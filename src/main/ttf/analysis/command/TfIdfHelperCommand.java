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

import ttf.analysis.context.AnalysisContext;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;
import ttf.persistence.ModelStore;


public class TfIdfHelperCommand implements Command {
	private final Log log = LogFactory.getLog(TopicLoadingCommand.class);

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		ModelStore store = ctx.getModelStore();

		PropertyGroup <String, NumericalValue> Appearancy = store.loadAppearancy();
		ctx.setTokenAppearancy(Appearancy);

		System.out.println("Loaded " + Appearancy.size() + " appearancies.");
		log.debug("Loaded " + Appearancy.size() + " appearancies.");
	
		double noArticles = store.loadNrOfArticles();
		ctx.setTotalArticles(noArticles);
		
		System.out.println("Loaded " + noArticles + " articles no.");
		log.debug("Loaded " + noArticles + " articles no.");

		return false;
	}
}
