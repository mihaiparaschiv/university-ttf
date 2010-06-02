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
package ttf.util;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.dbcp.BasicDataSource;

import ttf.analysis.computation.SimilarityComputer;
import ttf.analysis.context.ContextFactory;
import ttf.analysis.tfidf.TfIdf;
import ttf.model.article.ArticleFactory;
import ttf.model.article.BasicArticleFactory;
import ttf.model.topic.BasicTopicFactory;
import ttf.model.topic.TopicFactory;
import ttf.persistence.ModelStore;
import ttf.persistence.sql.SQLStore;
import ttf.util.alchemyapi.EntityDetector;
import ttf.util.tfidfapi.TfIdfDetector;

import com.orchestr8.api.AlchemyAPI;

/**
 * This class should be used only at the start of the application.
 * 
 * @author Mihai Paraschiv
 */
public class AppContext {
	private final DataSource dataSource;
	private final ArticleFactory articleFactory;
	private final TopicFactory topicFactory;
	private final ModelStore modelStore;
	private final ContextFactory contextFactory;

	private AppContext(Configuration c) {
		// data source
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(c.getString("db.driverClassName"));
		bds.setUsername(c.getString("db.username"));
		bds.setPassword(c.getString("db.password"));
		bds.setUrl(c.getString("db.uri"));
		dataSource = bds;

		// model handlers
		articleFactory = new BasicArticleFactory();
		topicFactory = new BasicTopicFactory();
		modelStore = new SQLStore(dataSource, articleFactory, topicFactory);

		// processing
		String key = c.getString("alchemy.key");
		AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(key);
		EntityDetector entityDetector = new EntityDetector(alchemyAPI);
		TfIdfDetector tfIdfDectector = new TfIdfDetector(new TfIdf());
		SimilarityComputer similarityComputer = new SimilarityComputer();

		contextFactory = new ContextFactory( //
				articleFactory, topicFactory, //
				modelStore, //
				alchemyAPI, entityDetector, tfIdfDectector, similarityComputer);
	}

	public static AppContext build(Configuration c) {
		return new AppContext(c);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public ArticleFactory getArticleFactory() {
		return articleFactory;
	}

	public TopicFactory getTopicFactory() {
		return topicFactory;
	}

	public ModelStore getModelStore() {
		return modelStore;
	}

	public ContextFactory getContextFactory() {
		return contextFactory;
	}
}
