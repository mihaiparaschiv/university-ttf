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

import ttf.model.article.Article;
import ttf.model.topic.Topic;
import ttf.persistence.ArticleStorage;
import ttf.persistence.TopicStorage;
import ttf.persistence.query.IdQuery;
import ttf.persistence.query.ModelQuery;

public class SQLStorage implements ArticleStorage, TopicStorage {

	@Override
	public Article loadArticleById(IdQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Article> loadArticles(ModelQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Article model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Topic loadTopicById(IdQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Topic> loadTopics(ModelQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Topic model) {
		// TODO Auto-generated method stub
		
	}

}
