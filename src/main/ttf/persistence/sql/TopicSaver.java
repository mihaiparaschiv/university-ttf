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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.dbutils.GenKeyQueryRunner;
import org.apache.commons.dbutils.QueryRunner;

import ttf.model.property.NumericalValue;
import ttf.model.topic.Topic;

public class TopicSaver {
	private final DataSource dataSource;

	protected TopicSaver(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected void save(Topic topic) throws SQLException {
		// insert topic if needed
		if (topic.getId() == null) {
			insertTopic(topic, dataSource);
		}

		QueryRunner run = new QueryRunner(dataSource);
		String sql;

		// delete all features
		sql = "DELETE FROM TopicFeatures WHERE topicId = ?";
		run.update(sql, topic.getId());

		// save terms and entities
		Set<Entry<String, NumericalValue>> terms = topic.getTermGroup()
				.entrySet();
		Set<Entry<String, NumericalValue>> entities = topic.getEntityGroup()
				.entrySet();

		List<Object[]> rows = new LinkedList<Object[]>();

		for (Entry<String, NumericalValue> entry : terms) {
			Object[] row = { "term", entry.getKey(), topic.getId(),
					entry.getValue() };
			rows.add(row);
		}

		for (Entry<String, NumericalValue> entry : entities) {
			Object[] row = { "entity", entry.getKey(), topic.getId(),
					entry.getValue() };
			rows.add(row);
		}

		sql = "INSERT INTO TopicFeatures (type, name, topicId, score) "
				+ "VALUES (?, ?, ?, ?)";
		// run.batch(sql, rows.toArray(new Object[0]));
	}

	private void insertTopic(Topic topic, DataSource dataSource)
			throws SQLException {
		GenKeyQueryRunner<String> run;
		run = new GenKeyQueryRunner<String>(dataSource, new IdResultHandler());

		String sql = "INSERT INTO Topics (title) VALUES (?)";
		run.update(sql, topic.getTitle());
		String id = run.getGeneratedKeys();
		topic.setId(id);
	}
}
