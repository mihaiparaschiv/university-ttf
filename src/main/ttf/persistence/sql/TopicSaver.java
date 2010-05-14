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

import ttf.model.topic.Topic;

public class TopicSaver extends ModelSaver<Topic> {
	protected TopicSaver(DataSource dataSource) {
		super(dataSource, new FeatureSaver());
	}

	@Override
	protected void save(Topic topic) throws SQLException {
		if (topic.getId() == null) {
			insert(topic);
		}

		QueryRunner run = new QueryRunner(dataSource);
		featureSaver.saveTopicFeatures(run, topic);
	}

	private void insert(Topic topic) throws SQLException {
		GenKeyQueryRunner<String> run;
		run = new GenKeyQueryRunner<String>(dataSource, new IdResultHandler());

		String sql = "INSERT INTO Topics (title) VALUES (?)";
		run.update(sql, topic.getTitle());
		String id = run.getGeneratedKeys();
		topic.setId(id);
	}
}
