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
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import ttf.analysis.context.AnalysisContext;
import ttf.model.IdFactory;
import ttf.model.property.NumericalValue;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;

/**
 * This {@link Command} loads topics from the database.
 * 
 * The code is very inefficient. The topics should be saved in cache and
 * conditions for topic selection should be added.
 * 
 * The loaded models are incomplete, because they do not link to entries.
 * 
 * The query processing code should be changed.
 * 
 * @author Mihai Paraschiv
 */
public class TopicLoadingCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		DataSource dataSource = ctx.getDataSource();

		QueryRunner run = new QueryRunner(dataSource);
		String sql = "SELECT (id, title) FROM Topics";
		ResultSetHandler<Collection<Topic>> rsh = new TopicListRSH(ctx);
		Collection<Topic> topics = run.query(sql, rsh);

		ctx.setLoadedTopics(topics);

		return false;
	}

	private class TopicListRSH implements ResultSetHandler<Collection<Topic>> {
		private AnalysisContext context;

		public TopicListRSH(AnalysisContext context) {
			this.context = context;
		}

		@Override
		public Collection<Topic> handle(ResultSet rs) throws SQLException {
			DataSource dataSource = context.getDataSource();
			QueryRunner run = new QueryRunner(dataSource);
			TopicFactory topicFactory = context.getTopicFactory();
			IdFactory idFactory = context.getIdFactory();

			List<Topic> topics = new LinkedList<Topic>();

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
					} else if (type == "term") {
						topic.getTermGroup().put(name,
								new NumericalValue(score));
					}
				}

				topics.add(topic);
			}

			return topics;
		}
	}
}
