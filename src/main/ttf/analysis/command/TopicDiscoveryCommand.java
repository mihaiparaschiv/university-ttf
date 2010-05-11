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
package ttf.analysis.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import ttf.analysis.context.SimpleContext;
import ttf.model.IdFactory;
import ttf.model.article.Article;
import ttf.model.property.value.NumericalValue;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;
import ttf.util.AppContext;

/**
 * This class handles the routing of the article to its most similar topic.
 * 
 * The code is very inefficient.
 * 
 * @author Mihai Paraschiv
 */
public class TopicDiscoveryCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		Article article = ((SimpleContext) context).getArticle();
		DataSource dataSource = AppContext.getInstance().getDataSource();
		QueryRunner run = new QueryRunner(dataSource);

		// find all topics
		String sql = "SELECT (id, title) FROM Topics";
		ResultSetHandler<List<Topic>> rsh = new TopicListRSH();
		List<Topic> topics = run.query(sql, rsh);

		for (Topic topic : topics) {

		}

		return false;
	}

	private class TopicListRSH implements ResultSetHandler<List<Topic>> {
		@Override
		public List<Topic> handle(ResultSet rs) throws SQLException {
			List<Topic> topics = new LinkedList<Topic>();
			DataSource dataSource = AppContext.getInstance().getDataSource();
			QueryRunner run = new QueryRunner(dataSource);
			TopicFactory topicFactory = AppContext.getInstance()
					.getTopicFactory();
			IdFactory idFactory = AppContext.getInstance().getIdFactory();

			while (rs.next()) {
				Topic topic = topicFactory.build();

				// properties
				topic.setId(idFactory.build(Topic.class, rs.getString(0)));
				topic.setTitle(rs.getString(1));

				// features
				String sql = "SELECT (type, name, score) FROM TopicFeatures WHERE topicId = ?";
				ArrayListHandler h = new ArrayListHandler();
				List<Object[]> features = run.query(sql, h, //
						topic.getId().getValue());
				for (Object[] o : features) {
					String type = (String) o[0];
					String name = (String) o[1];
					Double score = (Double) o[2];
					if (type == "entity") {
						topic.getEntityGroup().put(name,
								new NumericalValue(score));
					}
				}

				topics.add(topic);
			}

			return topics;
		}
	}
}
