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
package ttf.model.topic;

import ttf.model.Model;
import ttf.model.ModelId;
import ttf.model.article.ArticleKey;
import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.key.StringKey;
import ttf.model.property.value.DoubleValue;
import ttf.model.property.value.TextValue;

/**
 * A topic contains articles and subtopics.
 * 
 * @author Mihai Paraschiv
 */
public class Topic extends Model {
	protected final Property<TextValue> title;
	
	protected final PropertyGroup<ArticleKey, DoubleValue> articleGroup;
	protected final PropertyGroup<StringKey, DoubleValue> termGroup;
	protected final PropertyGroup<StringKey, DoubleValue> entityGroup;

	protected Topic(ModelId id, Property<TextValue> title,
			PropertyGroup<ArticleKey, DoubleValue> articleGroup,
			PropertyGroup<StringKey, DoubleValue> termGroup,
			PropertyGroup<StringKey, DoubleValue> entityGroup) {
		super(id);
		this.title = title;
		this.articleGroup = articleGroup;
		this.termGroup = termGroup;
		this.entityGroup = entityGroup;
	}

	public Property<TextValue> getTitle() {
		return title;
	}

	public PropertyGroup<ArticleKey, DoubleValue> getArticleGroup() {
		return articleGroup;
	}
	
	public PropertyGroup<StringKey, DoubleValue> getTermGroup() {
		return termGroup;
	}
	
	public PropertyGroup<StringKey, DoubleValue> getEntityGroup() {
		return entityGroup;
	}
}
