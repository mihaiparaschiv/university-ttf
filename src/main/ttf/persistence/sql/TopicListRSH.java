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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import ttf.model.property.NumericalValue;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;

/**
 * This class will be refactored.
 * 
 * @author Mihai Paraschiv
 *
 */
public class TopicListRSH implements ResultSetHandler<Collection<Topic>> {
	private final DataSource dataSource;
	private final TopicFactory topicFactory;

	public TopicListRSH(DataSource dataSource, TopicFactory topicFactory) {
		this.dataSource = dataSource;
		this.topicFactory = topicFactory;
	}

	@Override
	public Collection<Topic> handle(ResultSet rs) throws SQLException {
		QueryRunner run = new QueryRunner(dataSource);

		List<Topic> topics = new LinkedList<Topic>();

		while (rs.next()) {
			Topic topic = topicFactory.build(rs.getString(1));
			topic.setTitle(rs.getString(2));

			// load features
			String sql = "SELECT type, name, score FROM TopicFeatures WHERE topicId = ?";
			ArrayListHandler h = new ArrayListHandler();
			List<Object[]> features = run.query(sql, h, topic.getId());

			// parse features
			for (Object[] o : features) {
				String type = (String) o[0];
				String name = (String) o[1];
				Double score = (Double) o[2];
				if (type.equals("entity")) {
					topic.getEntityGroup().put(name, new NumericalValue(score));
				} else if (type.equals("term")) {
					topic.getTermGroup().put(name, new NumericalValue(score));
				}
			}

			topics.add(topic);
		}

		return topics;
	}
}
