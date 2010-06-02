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

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.context.AnalysisContext;
import ttf.model.article.Article;
import ttf.model.topic.Topic;
import ttf.persistence.ModelStore;

/**
 * This {@link Command} saves the article and the topic.
 * 
 * @author Mihai Paraschiv
 */
public class ModelPersistenceCommand implements Command {
	private final Log log = LogFactory.getLog(ModelPersistenceCommand.class);

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		ModelStore modelStore = ctx.getModelStore();

		Article article = ctx.getProcessedArticle();
		Topic topic = ctx.getSelectedTopic();

		modelStore.persistTopic(topic);
		log.debug("Saved topic: " + topic);
		modelStore.persistArticle(article);
		log.debug("Saved article: " + article);

		return false;
	}
}
