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
import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.util.alchemyapi.AlchemyEntity;
import ttf.util.alchemyapi.EntityDetector;

import com.orchestr8.api.AlchemyAPI;

/**
 * Detects topics using {@link AlchemyAPI}.
 * 
 * @author Mihai Paraschiv
 */
public class EntityDetectionCommand implements Command {
	private final Log log = LogFactory.getLog(EntityDetectionCommand.class);

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		EntityDetector detector = ctx.getEntityDetector();
		Article article = ((AnalysisContext) context).getProcessedArticle();

		String address = article.getAddress();

		Collection<AlchemyEntity> entities = detector
				.getEntitiesForURL(address);

		PropertyGroup<String, NumericalValue> entityGroup;
		entityGroup = article.getEntityGroup();

		for (AlchemyEntity entity : entities) {
			String key = entity.getText();
			NumericalValue value = new NumericalValue(entity.getRelevance());
			entityGroup.put(key, value);
		}

		log.debug("Found: " + entityGroup.size() + " entities.");

		return false;
	}
}
