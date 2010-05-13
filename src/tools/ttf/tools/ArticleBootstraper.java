/*
 * Copyright 2010 Mihai Paraschiv
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ttf.tools;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbutils.QueryRunner;

import ttf.incoming.FeedEntryParser;
import ttf.incoming.IncomingArticle;
import ttf.util.FactoryUtil;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * This class should can be used to build an initial collections of Articles for
 * testing purposes.
 * 
 * @author Mihai Paraschiv
 */
public class ArticleBootstraper {
	private static final String CONFIG_FILE = "resources/base.properties";
	private static final String TABLE = "IncomingArticles";
	private final DataSource dataSource;

	public ArticleBootstraper(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) throws IllegalArgumentException,
			FeedException, IOException, ConfigurationException, SQLException {
		Configuration config = new PropertiesConfiguration(CONFIG_FILE);
		DataSource dataSource = FactoryUtil.buildDataSource(config);
		ArticleBootstraper bs = new ArticleBootstraper(dataSource);
		bs.clear();
		bs.fill(args);
	}

	public void fill(String[] feedAddresses) throws IllegalArgumentException,
			FeedException, IOException, SQLException {
		QueryRunner run = new QueryRunner(dataSource);
		FeedEntryParser entryParser = new FeedEntryParser();

		String sql = "INSERT INTO "
				+ TABLE
				+ " (address, title, author, publishedAt, discoveredAt, content)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		for (String feedAddress : feedAddresses) {
			URL feedSource = new URL(feedAddress);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));

			for (Object e : feed.getEntries()) {
				IncomingArticle article = entryParser.parse((SyndEntry) e);

				run.update(sql, //
						article.getAddress(), //
						article.getTitle(), //
						article.getAuthor(), //
						article.getDiscoveredAt(), //
						article.getDiscoveredAt(), //
						article.getContent());
			}

			System.out.println("Ready: " + feedAddress);
		}
	}

	private void clear() throws SQLException {
		QueryRunner run = new QueryRunner(dataSource);
		String sql = "DELETE FROM " + TABLE;
		run.update(sql);
	}
}
