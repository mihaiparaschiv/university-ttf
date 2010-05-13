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
package ttf.analysis.context;

import javax.sql.DataSource;

import ttf.analysis.SimilarityComputer;
import ttf.model.IdFactory;
import ttf.model.article.ArticleFactory;
import ttf.model.topic.TopicFactory;
import ttf.util.alchemyapi.EntityDetector;

import com.orchestr8.api.AlchemyAPI;

public class ContextFactory {
	private final DataSource dataSource;
	private final AlchemyAPI alchemyAPI;
	private final ArticleFactory articleFactory;
	private final TopicFactory topicFactory;
	private final IdFactory idFactory;
	private final EntityDetector entityDetector;
	private final SimilarityComputer similarityComputer;

	public ContextFactory(DataSource dataSource, AlchemyAPI alchemyAPI,
			ArticleFactory articleFactory, TopicFactory topicFactory,
			IdFactory idFactory, EntityDetector entityDetector,
			SimilarityComputer similarityComputer) {
		super();
		this.dataSource = dataSource;
		this.alchemyAPI = alchemyAPI;
		this.articleFactory = articleFactory;
		this.topicFactory = topicFactory;
		this.idFactory = idFactory;
		this.entityDetector = entityDetector;
		this.similarityComputer = similarityComputer;
	}

	public AnalysisContext build() {
		AnalysisContext context = new AnalysisContext();

		context.setDataSource(dataSource);
		context.setAlchemyAPI(alchemyAPI);
		context.setArticleFactory(articleFactory);
		context.setTopicFactory(topicFactory);
		context.setIdFactory(idFactory);
		context.setEntityDetector(entityDetector);
		context.setSimilarityComputer(similarityComputer);

		return context;
	}
}
