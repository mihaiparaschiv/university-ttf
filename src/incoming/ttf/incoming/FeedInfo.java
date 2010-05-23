package ttf.incoming;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

class FeedInfo implements Comparable<FeedInfo> {
    public static final long MINUTE = 60 * 1000;
    private static final int MAGIC_NUMBER = 5;
    public String address;
    public int interval;
    Timestamp lastCheck;

    public FeedInfo(String address, int interval, Timestamp lastCheck) {
        this.address = address;
        this.interval = interval;
        this.lastCheck = lastCheck;
    }

    public int compareTo(FeedInfo o) {
        return Long.signum(lastCheck.getTime() + interval * MINUTE - o.lastCheck.getTime() - o.interval
                * MINUTE);
    }

    public String toString() {
        return address + " " + interval + " " + lastCheck;
    }

    public void update(SyndFeed feed) {
        lastCheck = new Timestamp((new Date()).getTime());
        List entries = feed.getEntries();
        if (entries.size() > 1) {
            SyndEntry firstEntry = (SyndEntry) entries.get(0);
            SyndEntry lastEntry = (SyndEntry) entries.get(entries.size() - 1);

            int entriesAverage = (int) (Math.abs(firstEntry.getPublishedDate().getTime() - lastEntry
                    .getPublishedDate().getTime()) / (MINUTE * (entries.size() - 1)));
            if (interval == 0)
                interval = entriesAverage;
            else
                interval = (interval * (MAGIC_NUMBER - 1) + entriesAverage) / MAGIC_NUMBER;
        }
    }
}