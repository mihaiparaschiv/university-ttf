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

import java.util.Map.Entry;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.context.AnalysisContext;
import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;

/**
 * This class will be refactored.
 * 
 * @author Mihai Paraschiv
 */
public class TopicUpdateCommand implements Command {
	private final Log log = LogFactory.getLog(TopicUpdateCommand.class);

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		Article article = ctx.getProcessedArticle();
		Topic topic = ctx.getSelectedTopic();

		updateTopicGroup(topic.getTermGroup(), article.getTermGroup());
		updateTopicGroup(topic.getEntityGroup(), article.getEntityGroup());

		log.debug("Topic updated: " + topic);

		return false;
	}

	private void updateTopicGroup(
			PropertyGroup<String, NumericalValue> topicGroup,
			PropertyGroup<String, NumericalValue> articleGroup) {
		for (Entry<String, NumericalValue> e : articleGroup.entrySet()) {
			String key = e.getKey();

			// article value
			NumericalValue av = e.getValue();

			// topic value - update
			NumericalValue tv = topicGroup.get(key);
			if (tv == null) {
				tv = new NumericalValue(av.getDouble());
			} else {
				tv = new NumericalValue(tv.getDouble() + av.getDouble());
			}

			topicGroup.put(key, tv);
		}
	}
}
