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

import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.chain.impl.ContextBase;

import ttf.analysis.SimilarityComputer;
import ttf.model.IdFactory;
import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;
import ttf.util.alchemyapi.EntityDetector;

import com.orchestr8.api.AlchemyAPI;

public class AnalysisContext extends ContextBase {
	private static final long serialVersionUID = -1884835131118409894L;

	// general - assigned by a factory
	private DataSource dataSource;
	private AlchemyAPI alchemyAPI;
	private ArticleFactory articleFactory;
	private TopicFactory topicFactory;
	private IdFactory idFactory;
	private EntityDetector entityDetector;
	private SimilarityComputer similarityComputer;

	// workflow
	private Article currentArticle;
	private Collection<Topic> loadedTopics;
	private Topic selectedTopic;

	protected AnalysisContext() {
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public AlchemyAPI getAlchemyAPI() {
		return alchemyAPI;
	}

	public void setAlchemyAPI(AlchemyAPI alchemyAPI) {
		this.alchemyAPI = alchemyAPI;
	}

	public ArticleFactory getArticleFactory() {
		return articleFactory;
	}

	public void setArticleFactory(ArticleFactory articleFactory) {
		this.articleFactory = articleFactory;
	}

	public TopicFactory getTopicFactory() {
		return topicFactory;
	}

	public void setTopicFactory(TopicFactory topicFactory) {
		this.topicFactory = topicFactory;
	}

	public IdFactory getIdFactory() {
		return idFactory;
	}

	public void setIdFactory(IdFactory idFactory) {
		this.idFactory = idFactory;
	}

	public EntityDetector getEntityDetector() {
		return entityDetector;
	}

	public void setEntityDetector(EntityDetector entityDetector) {
		this.entityDetector = entityDetector;
	}

	public SimilarityComputer getSimilarityComputer() {
		return similarityComputer;
	}

	public void setSimilarityComputer(SimilarityComputer similarityComputer) {
		this.similarityComputer = similarityComputer;
	}

	public Article getCurrentArticle() {
		return currentArticle;
	}

	public void setCurrentArticle(Article currentArticle) {
		this.currentArticle = currentArticle;
	}

	public Collection<Topic> getLoadedTopics() {
		return loadedTopics;
	}

	public void setLoadedTopics(Collection<Topic> loadedTopics) {
		this.loadedTopics = loadedTopics;
	}

	public Topic getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(Topic selectedTopic) {
		this.selectedTopic = selectedTopic;
	}
}
