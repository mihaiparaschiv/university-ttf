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
import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.util.AppContext;
import ttf.util.alchemyapi.AlchemyEntity;
import ttf.util.alchemyapi.EntityDetector;

public class EntityDetectionCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		EntityDetector detector = AppContext.getInstance().getEntityDetector();
		Article article = ((AnalysisContext) context).getCurrentArticle();

		String address = article.getAddress();

		Collection<AlchemyEntity> entities = detector
				.getEntitiesForURL(address);

		PropertyGroup<String, NumericalValue> entityGroup;
		entityGroup = article.getEntityGroup();

		for (AlchemyEntity entity : entities) {
			String key = entity.getText();
			NumericalValue value = new NumericalValue(entity.getRelevance());
			entityGroup.put(key , value);
		}

		return false;
	}
}
