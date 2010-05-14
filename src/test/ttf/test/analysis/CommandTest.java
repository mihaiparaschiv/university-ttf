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
import ttf.analysis.context.AnalysisContext;
import ttf.analysis.context.ContextFactory;
import ttf.incoming.BasicTransformer;
import ttf.incoming.FeedEntryParser;
import ttf.incoming.IncomingArticle;
import ttf.model.article.Article;
import ttf.test.TestUtil;
import ttf.util.AppContext;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class CommandTest {
	private static final String FEED = "http://feeds.feedburner.com/TechCrunch";

	private static ContextFactory contextFactory;
	private static FeedEntryParser entryParser;
	private static BasicTransformer transformer;
	private static SyndFeed feed;

	@BeforeClass
	public static void beforeClass() throws IllegalArgumentException,
			FeedException, IOException, XPathExpressionException {
		Configuration config = TestUtil.getDefaultConfiguration();
		AppContext appContext = AppContext.build(config);
		contextFactory = appContext.getContextFactory();
		entryParser = new FeedEntryParser();
		transformer = new BasicTransformer(appContext.getArticleFactory());

		// load the feed
		URL feedSource = new URL(FEED);
		SyndFeedInput input = new SyndFeedInput();
		feed = input.build(new XmlReader(feedSource));
	}

	@Test
	public void entityDetection() throws Exception {
		// Build an article from the first entry of the feed
		Object e = feed.getEntries().get(0);
		IncomingArticle incomingArticle = entryParser.parse((SyndEntry) e);
		Article article = transformer.transform(incomingArticle);

		// analyze article
		AnalysisContext context = contextFactory.build();
		context.setIncomingArticle(article);
		Command command = new EntityDetectionCommand();
		command.execute(context);
		System.out.println(article.getEntityGroup());
	}
}
