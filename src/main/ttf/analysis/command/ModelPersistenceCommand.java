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

import ttf.analysis.context.AnalysisContext;
import ttf.model.article.Article;
import ttf.model.topic.Topic;
import ttf.persistence.ModelStore;

/**
 * This {@link Command} saves the article and creates a topic if needed.
 * 
 * @author Mihai Paraschiv
 */
public class ModelPersistenceCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		ModelStore modelStore = ctx.getModelStore();

		Article article = ctx.getIncomingArticle();
		Topic topic = ctx.getSelectedTopic();

		if (topic == null) {
			topic = modelStore.buildTopic();
			topic.setTitle(article.getTitle());
		}
		
		modelStore.persistTopic(topic);
		modelStore.persistArticle(article);

		return false;
	}
}
