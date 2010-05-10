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
import ttf.model.property.key.StringKey;
import ttf.model.property.value.DoubleValue;
import ttf.util.alchemyapi.AlchemyEntity;
import ttf.util.alchemyapi.EntityProvider;

public class EntityDetectionCommand implements Command {
	@Override
	public boolean execute(Context context) throws Exception {
		EntityProvider provider = ((SimpleContext) context).getEntityProvider();
		Article article = ((SimpleContext) context).getArticle();

		String content = article.getContent().getValue().get();
		Collection<AlchemyEntity> entities = provider
				.getEntitiesForURL(content);

		PropertyGroup<StringKey, DoubleValue> entityGroup;
		entityGroup = article.getEntityGroup();

		for (AlchemyEntity entity : entities) {
			KeyedProperty<StringKey, DoubleValue> p;
			p = new KeyedProperty<StringKey, DoubleValue>( //
					new StringKey(entity.getText()), //
					new DoubleValue(entity.getRelevance()));
			entityGroup.put(p);
		}

		return false;
	}
}
