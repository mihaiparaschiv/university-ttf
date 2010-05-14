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
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.chain.Command;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.AnalysisController;
import ttf.analysis.context.ContextFactory;
import ttf.analysis.input.InternalProvider;
import ttf.analysis.processor.ChainProcessor;
import ttf.incoming.BasicTransformer;
import ttf.incoming.FeedEntryParser;
import ttf.incoming.IncomingArticle;
import ttf.incoming.Transformer;
import ttf.model.article.Article;
import ttf.test.TestUtil;
import ttf.util.AppContext;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * This class provides a simple way of testing topic detection.
 * 
 * Articles are read from feeds given as command line arguments and are
 * processed by an {@link AnalysisController}.
 * 
 * @author Mihai Paraschiv
 */
public class BasicApp {
	Log log = LogFactory.getLog(BasicApp.class);
	private final Configuration configuration;
	private final ContextFactory contextFactory;
	private final FeedEntryParser feedEntryParser;
	private final Transformer transformer;

	public BasicApp(Configuration configuration, ContextFactory contextFactory,
			FeedEntryParser feedEntryParser, Transformer transformer) {
		this.configuration = configuration;
		this.contextFactory = contextFactory;
		this.feedEntryParser = feedEntryParser;
		this.transformer = transformer;
	}

	public static void main(String[] args) throws IllegalArgumentException,
			FeedException, IOException, ConfigurationException, SQLException {
		Configuration config = TestUtil.getDefaultConfiguration();
		AppContext appContext = AppContext.build(config);

		FeedEntryParser parser = new FeedEntryParser();
		Transformer transformer = new BasicTransformer(appContext
				.getArticleFactory());

		BasicApp app = new BasicApp(config, appContext.getContextFactory(),
				parser, transformer);
		app.start(args);
	}

	public void start(String[] feedAddresses) throws IllegalArgumentException,
			FeedException, IOException, SQLException {
		// setup commands
		List<Command> commands = new LinkedList<Command>();

		// add articles
		InternalProvider provider = new InternalProvider();
		for (String address : feedAddresses) {
			// load the feed
			URL feedSource = new URL(address);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));

			// create articles
			for (Object e : feed.getEntries()) {
				IncomingArticle incomingArticle = feedEntryParser
						.parse((SyndEntry) e);
				Article article = transformer.transform(incomingArticle);
				provider.add(article);
			}

			log.info("Ready: " + address);
		}

		// start processing
		ChainProcessor processor = new ChainProcessor(contextFactory, commands);
		AnalysisController controller = new AnalysisController(provider,
				processor);
		controller.run();
	}
}
