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
package ttf.test.analysis;

import java.io.IOException;
import java.net.URL;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.chain.Command;
import org.apache.commons.configuration.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import ttf.analysis.command.EntityDetectionCommand;
import ttf.analysis.context.SimpleContext;
import ttf.incoming.FeedEntryParser;
import ttf.model.article.Article;
import ttf.util.ConfigurationProvider;
import ttf.util.alchemyapi.EntityProvider;

import com.orchestr8.api.AlchemyAPI;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class CommandTest {
	private static final String FEED = "http://feeds.feedburner.com/TechCrunch";
	private static Article article;
	private static EntityProvider entityProvider;

	@BeforeClass
	public static void beforeClass() throws IllegalArgumentException,
			FeedException, IOException, XPathExpressionException {
		// Alchemy API
		Configuration config = ConfigurationProvider.getDefault();
		String key = config.getString("alchemy.key");
		AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(key);
		entityProvider = new EntityProvider(alchemyAPI);

		// Load the feed
		URL feedSource = new URL(FEED);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));

		// Build an article from the first entry of the feed
		FeedEntryParser entryParser = new FeedEntryParser();
		Object e = feed.getEntries().get(0);
		article = entryParser.parse((SyndEntry) e);
	}

	@Test
	public void entityDetection() throws Exception {
		SimpleContext context = new SimpleContext();
		context.setArticle(article);
		context.setEntityProvider(entityProvider);
		Command command = new EntityDetectionCommand();
		command.execute(context);
		System.out.println(article.getEntityGroup());
	}
}
