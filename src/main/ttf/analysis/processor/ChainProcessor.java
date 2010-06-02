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
package ttf.analysis.processor;

import java.util.List;

import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.context.AnalysisContext;
import ttf.analysis.context.ContextFactory;
import ttf.model.article.Article;

public class ChainProcessor implements Processor {
	private final Log log = LogFactory.getLog(ChainProcessor.class);

	private final ContextFactory contextFactory;
	private final List<Command> commands;

	public ChainProcessor(ContextFactory contextFactory, List<Command> commands) {
		this.contextFactory = contextFactory;
		this.commands = commands;
	}

	@Override
	public void process(Article article) throws Exception {
		log.info("Start: " + article);

		Chain chain = new ChainBase(commands);
		AnalysisContext context = contextFactory.build();
		context.setIncomingArticle(article);
		chain.execute(context);
	}
}
