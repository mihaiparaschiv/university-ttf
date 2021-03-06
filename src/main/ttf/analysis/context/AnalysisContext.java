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

import org.apache.commons.chain.impl.ContextBase;

import ttf.analysis.computation.SimilarityComputer;
import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;
import ttf.model.property.HashMapPropertyGroup;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;
import ttf.persistence.ModelStore;
import ttf.util.alchemyapi.EntityDetector;
import ttf.util.tfidfapi.TfIdfDetector;

import com.orchestr8.api.AlchemyAPI;

public class AnalysisContext extends ContextBase {
	private static final long serialVersionUID = -1884835131118409894L;

	// general - assigned by a factory
	private ArticleFactory articleFactory;
	private TopicFactory topicFactory;
	private ModelStore modelStore;
	private AlchemyAPI alchemyAPI;
	private EntityDetector entityDetector;
	private TfIdfDetector tfIdfDetector;
	private SimilarityComputer similarityComputer;

	// workflow
	private Article processedArticle;
	private Collection<Topic> loadedTopics;
	private Topic selectedTopic;
	private PropertyGroup<String, NumericalValue> TokenAppearancy;
	private PropertyGroup<String, NumericalValue> Idf;
	private double TotalArticles;

	protected AnalysisContext() {
		Idf = new HashMapPropertyGroup<String, NumericalValue>();
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

	public ModelStore getModelStore() {
		return modelStore;
	}

	public void setModelStore(ModelStore modelStore) {
		this.modelStore = modelStore;
	}

	public AlchemyAPI getAlchemyAPI() {
		return alchemyAPI;
	}

	public void setAlchemyAPI(AlchemyAPI alchemyAPI) {
		this.alchemyAPI = alchemyAPI;
	}

	public EntityDetector getEntityDetector() {
		return entityDetector;
	}

	public void setEntityDetector(EntityDetector entityDetector) {
		this.entityDetector = entityDetector;
	}

	public TfIdfDetector getTfIdfDetector() {
		return tfIdfDetector;
	}

	public void setTfIdfDetector(TfIdfDetector tfIdfDetector) {
		this.tfIdfDetector = tfIdfDetector;
	}

	public SimilarityComputer getSimilarityComputer() {
		return similarityComputer;
	}

	public void setSimilarityComputer(SimilarityComputer similarityComputer) {
		this.similarityComputer = similarityComputer;
	}

	public Article getProcessedArticle() {
		return processedArticle;
	}

	public void setIncomingArticle(Article processedArticle) {
		this.processedArticle = processedArticle;
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

	public void setTokenAppearancy(
			PropertyGroup<String, NumericalValue> tokenAppearancy) {
		TokenAppearancy = tokenAppearancy;
	}

	public PropertyGroup<String, NumericalValue> getTokenAppearancy() {
		return TokenAppearancy;
	}

	public void setTotalArticles(double totalArticles) {
		TotalArticles = totalArticles;
	}

	public double getTotalArticles() {
		return TotalArticles;
	}

	public void setIdf(PropertyGroup<String, NumericalValue> idf) {
		Idf = idf;
	}

	public PropertyGroup<String, NumericalValue> getIdf() {
		return Idf;
	}
}
