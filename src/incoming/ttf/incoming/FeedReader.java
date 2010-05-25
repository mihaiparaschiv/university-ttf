package ttf.incoming;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import ttf.util.AppContext;

public class FeedReader {
    private static final String CONFIG_FILE = "base.properties";
    private static final String INCOMINGARTICLES = "IncomingArticles";
    private static final String SOURCES = "sources";

    private DataSource dataSource;
    private PriorityQueue<FeedInfo> queue;

    public FeedReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void InitializeQueue() throws SQLException, MalformedURLException {
        queue = new PriorityQueue<FeedInfo>();

        QueryRunner run = new QueryRunner(dataSource);
        String sql = "SELECT address, retrievalInterval, lastChecked FROM " + SOURCES;
        ArrayListHandler h = new ArrayListHandler();
        List<Object[]> features = run.query(sql, h);

        // Read sources from database
        for (Object[] o : features) {
            String address = (String) o[0];
            Integer interval = (Integer) o[1]; // Interval in minutes
            Timestamp lastChecked = (Timestamp) o[2];

            queue.add(new FeedInfo(address, interval, lastChecked));
        }
    }

    public void run() throws IllegalArgumentException, FeedException, IOException, SQLException {
        while (queue.size() > 0) {
            FeedInfo feedInfo = queue.peek();
            Timestamp nextCheck = new Timestamp(feedInfo.lastCheck.getTime() + feedInfo.interval
                    * FeedInfo.MINUTE);
            Date current = new Date();

            // Sleep
            if (current.before(nextCheck))
                try {
                    Thread.sleep(nextCheck.getTime() - current.getTime());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            else {
                feedInfo = queue.poll();

                process(feedInfo);

                queue.add(feedInfo);
            }
        }
    }

    private void process(FeedInfo feedInfo) throws IllegalArgumentException, FeedException, IOException,
            SQLException {
        QueryRunner run = new QueryRunner(dataSource);
        FeedEntryParser entryParser = new FeedEntryParser();

        String check = "SELECT * FROM " + INCOMINGARTICLES + " WHERE address = ?";

        String sql = "INSERT INTO " + INCOMINGARTICLES
                + " (address, title, author, publishedAt, discoveredAt, content)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(feedInfo.address)));

        for (Object e : feed.getEntries()) {
            IncomingArticle article = entryParser.parse((SyndEntry) e);

            // Add if new
            List l = run.query(check, new ArrayListHandler(), article.getAddress());
            if (l.size() == 0) {
                run.update(sql, //
                        article.getAddress(), //
                        article.getTitle(), //
                        article.getAuthor(), //
                        article.getPublishedAt(), //
                        article.getDiscoveredAt(), //
                        article.getContent());
            }
        }

        feedInfo.update(feed);

        sql = "UPDATE " + SOURCES + " SET lastChecked = ?, retrievalInterval = ? where address = ?";
        run.update(sql, feedInfo.lastCheck, feedInfo.interval, feedInfo.address);
    }

    public static void main(String[] args) throws ConfigurationException, SQLException,
            IllegalArgumentException, FeedException, IOException {
        Configuration config = new PropertiesConfiguration(CONFIG_FILE);
        DataSource dataSource = AppContext.build(config).getDataSource();

        FeedReader feedReader = new FeedReader(dataSource);
        feedReader.InitializeQueue();
        feedReader.run();
    }
}
