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

import java.sql.SQLException;
import java.util.Collection;

import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;
import ttf.persistence.query.Query;

public interface ModelStore {

	public Collection<Article> loadArticles(Query query)
			throws PersistenceException;

	public void persistArticle(Article article) throws PersistenceException;

	public Collection<Topic> loadTopics(Query query)
			throws PersistenceException;

	public void persistTopic(Topic topic) throws PersistenceException;

	public PropertyGroup<String, NumericalValue> loadAppearancy()
			throws PersistenceException, SQLException;

	public double loadNrOfArticles() throws PersistenceException, SQLException;
}
