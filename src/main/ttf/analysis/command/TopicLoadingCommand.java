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

import ttf.analysis.context.AnalysisContext;
import ttf.model.topic.Topic;
import ttf.persistence.ModelStore;

/**
 * This {@link Command} loads topics from the database.
 * 
 * The code is very inefficient. The topics should be saved in cache and
 * conditions for topic selection should be added.
 * 
 * @author Mihai Paraschiv
 */
public class TopicLoadingCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		ModelStore store = ctx.getModelStore();

		Collection<Topic> topics = store.loadTopics(null);
		ctx.setLoadedTopics(topics);

		return false;
	}
}
