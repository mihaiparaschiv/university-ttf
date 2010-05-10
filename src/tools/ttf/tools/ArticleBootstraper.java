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
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbutils.QueryRunner;

import ttf.util.DataSourceProvider;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ArticleBootstraper {
	private static final String TABLE = "IncomingArticles";
	private final DataSource dataSource;

	public ArticleBootstraper() {
		dataSource = DataSourceProvider.getDefault();
	}

	public static void main(String[] args) throws IllegalArgumentException,
			FeedException, IOException, ConfigurationException, SQLException {
		ArticleBootstraper bs = new ArticleBootstraper();
		bs.clear();
		bs.fill(args);
	}

	public void fill(String[] feedAddresses) throws IllegalArgumentException,
			FeedException, IOException, SQLException {
		QueryRunner run = new QueryRunner(dataSource);

		for (String feedAddress : feedAddresses) {
			URL feedSource = new URL(feedAddress);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));

			for (Object e : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) e;
				String address = entry.getUri();
				String title = entry.getTitle();
				String author = entry.getAuthor();
				Date publishedAt = entry.getPublishedDate();
				Date discoveredAt = new Date();

				List<?> cList = entry.getContents();
				SyndContent c = null;
				if (cList.size() > 0) {
					c = (SyndContent) entry.getContents().get(0);
				} else {
					c = entry.getDescription();
				}
				String content = (c != null) ? c.getValue() : null;

				String sql = "INSERT INTO "
						+ TABLE
						+ " (address, title, author, publishedAt, discoveredAt, content)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
				run.update(sql, address, title, author, publishedAt,
						discoveredAt, content);
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