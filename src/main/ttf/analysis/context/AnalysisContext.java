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

import ttf.analysis.SimilarityComputer;
import ttf.model.article.Article;
import ttf.model.topic.Topic;

public class AnalysisContext extends ContextBase {
	private static final long serialVersionUID = -1884835131118409894L;

	private Article currentArticle;
	private Collection<Topic> loadedTopics;
	private Topic selectedTopic;
	private SimilarityComputer similarityComputer;

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

	public SimilarityComputer getSimilarityComputer() {
		return similarityComputer;
	}

	public void setSimilarityComputer(SimilarityComputer similarityComputer) {
		this.similarityComputer = similarityComputer;
	}
}
