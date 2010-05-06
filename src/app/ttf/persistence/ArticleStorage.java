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
package ttf.persistence;

import ttf.model.article.Article;
import ttf.persistence.query.IdQuery;
import ttf.persistence.query.ModelQuery;

public interface ArticleStorage {
	
	/**
	 * Loads an article by id.
	 * 
	 * @param query
	 * @return article with specified id or null
	 */
	public Article loadArticleById(IdQuery query);

	/**
	 * Loads a set of articles.
	 * 
	 * @param query
	 * @return models
	 */
	public Iterable<Article> loadArticles(ModelQuery query);

	/**
	 * Saves the model.
	 * 
	 * @param model
	 */
	public void persist(Article model);
}
