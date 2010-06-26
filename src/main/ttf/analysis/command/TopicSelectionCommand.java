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

import java.util.Collection;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.computation.SimilarityComputer;
import ttf.analysis.context.AnalysisContext;
import ttf.model.article.Article;
import ttf.model.topic.Topic;
import ttf.model.topic.TopicFactory;

/**
 * This class handles the routing of the incoming article. At most one topic is
 * selected as parent. If no topic is found, a new one is built.
 * 
 * The code needs to be changed because the selected topic is added to the
 * context and not to the article.
 * 
 * @author Mihai Paraschiv
 */
public class TopicSelectionCommand implements Command {
	private final Log log = LogFactory.getLog(TopicSelectionCommand.class);

	private final double minSimilarity;

	public TopicSelectionCommand(double minSimilarity) {
		this.minSimilarity = minSimilarity;
	}

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		Article article = ctx.getProcessedArticle();
		TopicFactory topicFactory = ctx.getTopicFactory();
		SimilarityComputer computer = ctx.getSimilarityComputer();

		Collection<Topic> topics = ctx.getLoadedTopics();

		Topic selectedTopic = null;
		double maxSimilarity = 0;
		for (Topic topic : topics) {
			double sim = computer.compute(article, topic, ctx);
			if (sim > maxSimilarity) {
				selectedTopic = topic;
				maxSimilarity = sim;
			}
		}

		if (maxSimilarity < minSimilarity) {
			selectedTopic = topicFactory.build();
			selectedTopic.setTitle(article.getTitle());
		}

		article.setTopic(selectedTopic);
		ctx.setSelectedTopic(selectedTopic);

		log.debug("Selected topic: " + selectedTopic);

		return false;
	}
}
