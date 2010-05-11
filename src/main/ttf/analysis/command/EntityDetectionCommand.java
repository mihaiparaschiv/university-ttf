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

import ttf.analysis.context.SimpleContext;
import ttf.model.article.Article;
import ttf.model.property.KeyedProperty;
import ttf.model.property.PropertyGroup;
import ttf.model.property.value.NumericalValue;
import ttf.util.alchemyapi.AlchemyEntity;
import ttf.util.alchemyapi.EntityProvider;

public class EntityDetectionCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		EntityProvider provider = ((SimpleContext) context).getEntityProvider();
		Article article = ((SimpleContext) context).getArticle();

		String address = article.getAddress().getValue();

		Collection<AlchemyEntity> entities = provider
				.getEntitiesForURL(address);

		PropertyGroup<String, NumericalValue> entityGroup;
		entityGroup = article.getEntityGroup();

		for (AlchemyEntity entity : entities) {
			KeyedProperty<String, NumericalValue> p;
			KeyedProperty<String, NumericalValue> old;
			p = new KeyedProperty<String, NumericalValue>( //
					entity.getText(), //
					new NumericalValue(entity.getRelevance()));

			old = entityGroup.get(p.getKey());
			if (old == null) {
				entityGroup.put(p);
			} else {
				double max = Math.max(p.getValue().getDouble(), old.getValue().getDouble());
				p.setValue(new NumericalValue(max));
			}
		}

		return false;
	}
}
