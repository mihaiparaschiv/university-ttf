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
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;

import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;

public class FeatureSaver {

	public void saveArticleFeatures(QueryRunner run, Article a)
			throws SQLException {
		String table = "ArticleFeatures";
		String fieldName = "articleId";
		String id = a.getId();
		deleteFeatures(run, table, fieldName, id);
		insertFeatures(run, table, fieldName, id, "term", a.getTermGroup());
		insertFeatures(run, table, fieldName, id, "entity", a.getEntityGroup());
	}

	public void saveTopicFeatures(QueryRunner run, Topic t) throws SQLException {
		String table = "TopicFeatures";
		String fieldName = "topicId";
		String id = t.getId();
		deleteFeatures(run, table, fieldName, id);
		insertFeatures(run, table, fieldName, id, "term", t.getTermGroup());
		insertFeatures(run, table, fieldName, id, "entity", t.getEntityGroup());
	}

	private void deleteFeatures(QueryRunner run, String table,
			String fieldName, String id) throws SQLException {
		String sql = "DELETE FROM " + table + " WHERE " + fieldName + " = ?";
		run.update(sql, id);
	}

	private void insertFeatures(QueryRunner run, String table,
			String fieldName, String id, String type,
			PropertyGroup<String, NumericalValue> group) throws SQLException {
		Object[][] rows = new Object[group.size()][];

		int i = 0;
		for (Entry<String, NumericalValue> entry : group.entrySet()) {
			Object[] row = { //
			type, //
					entry.getKey(), //
					entry.getValue().getDouble(), //
					id };
			rows[i++] = row;
		}

		String sql = "INSERT INTO " + table + " (type, name, score, "
				+ fieldName + ") " + "VALUES (?, ?, ?, ?)";
		run.batch(sql, rows);
	}
}
