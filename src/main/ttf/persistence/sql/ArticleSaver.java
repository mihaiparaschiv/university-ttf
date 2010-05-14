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

import javax.sql.DataSource;

import org.apache.commons.dbutils.GenKeyQueryRunner;
import org.apache.commons.dbutils.QueryRunner;

import ttf.model.article.Article;

public class ArticleSaver extends ModelSaver<Article> {
	protected ArticleSaver(DataSource dataSource) {
		super(dataSource, new FeatureSaver());
	}

	@Override
	protected void save(Article article) throws SQLException {
		if (article.getId() == null) {
			insert(article);
		}

		QueryRunner run = new QueryRunner(dataSource);
		featureSaver.saveArticleFeatures(run, article);
	}

	private void insert(Article article) throws SQLException {
		GenKeyQueryRunner<String> run;
		run = new GenKeyQueryRunner<String>(dataSource, new IdResultHandler());

		String sql = "INSERT INTO Articles"
				+ " (address, title, author, publishedAt, discoveredAt, content, topicId)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		run.update(sql, //
				article.getAddress(), //
				article.getTitle(), //
				article.getAuthor(), //
				article.getDiscoveredAt(), //
				article.getDiscoveredAt(), //
				article.getContent(), //
				article.getTopic().getId());
		
		String id = run.getGeneratedKeys();
		article.setId(id);
	}
}
