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

import ttf.analysis.SimilarityComputer;
import ttf.analysis.context.ContextFactory;
import ttf.model.article.ArticleFactory;
import ttf.model.topic.TopicFactory;
import ttf.persistence.ModelStore;
import ttf.persistence.sql.SQLStore;
import ttf.util.alchemyapi.EntityDetector;

import com.orchestr8.api.AlchemyAPI;

/**
 * This class should be used only at the start of the application.
 * 
 * @author Mihai Paraschiv
 */
public class FactoryUtil {
	public static ContextFactory buildContextFactory(Configuration c) {
		// mode store
		ModelStore modelStore = buildModelStore(c);

		// Alchemy API
		String key = c.getString("alchemy.key");
		AlchemyAPI alchemyAPI = AlchemyAPI.GetInstanceFromString(key);

		// processing
		EntityDetector entityDetector = new EntityDetector(alchemyAPI);
		SimilarityComputer similarityComputer = new SimilarityComputer();

		return new ContextFactory(modelStore, //
				alchemyAPI, //
				entityDetector, //
				similarityComputer);
	}

	public static DataSource buildDataSource(Configuration c) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(c.getString("db.driverClassName"));
		dataSource.setUsername(c.getString("db.username"));
		dataSource.setPassword(c.getString("db.password"));
		dataSource.setUrl(c.getString("db.uri"));
		return dataSource;
	}

	public static ModelStore buildModelStore(Configuration c) {
		DataSource dataSource = buildDataSource(c);
		ArticleFactory articleFactory = new ArticleFactory();
		TopicFactory topicFactory = new TopicFactory();

		return new SQLStore(dataSource, articleFactory, topicFactory);
	}
}
