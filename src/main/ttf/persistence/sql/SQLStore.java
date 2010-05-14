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
package ttf.persistence.sql;

import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang.NotImplementedException;

import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;
import ttf.persistence.ModelStore;
import ttf.persistence.PersistenceException;
import ttf.persistence.query.Query;

public class SQLStore implements ModelStore {
	private final DataSource dataSource;
	//private final ArticleFactory articleFactory;
	private final TopicFactory topicFactory;
	private final ArticleSaver articleSaver;
	private final TopicSaver topicSaver;

	public SQLStore(DataSource dataSource, ArticleFactory articleFactory,
			TopicFactory topicFactory) {
		this.dataSource = dataSource;
		// this.articleFactory = articleFactory;
		this.topicFactory = topicFactory;
		this.articleSaver = new ArticleSaver(dataSource);
		this.topicSaver = new TopicSaver(dataSource);
	}

	@Override
	public Collection<Article> loadArticles(Query query)
			throws PersistenceException {
		throw new NotImplementedException();
	}

	@Override
	public void persistArticle(Article article) throws PersistenceException {
		try {
			articleSaver.save(article);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Collection<Topic> loadTopics(Query query)
			throws PersistenceException {
		QueryRunner run = new QueryRunner(dataSource);
		String sql = "SELECT id, title FROM Topics";
		ResultSetHandler<Collection<Topic>> rsh = new TopicListRSH(dataSource,
				topicFactory);
		Collection<Topic> topics;
		try {
			topics = run.query(sql, rsh);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return topics;
	}

	@Override
	public void persistTopic(Topic topic) throws PersistenceException {
		try {
			topicSaver.save(topic);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
}
