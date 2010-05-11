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

import ttf.model.article.ArticleKey;
import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.key.StringKey;
import ttf.model.property.value.DoubleValue;
import ttf.model.property.value.TextValue;

public class TopicFactory {
	public Topic build() {
		// properties
		Property<TextValue> title = new Property<TextValue>(null);

		// groups
		PropertyGroup<ArticleKey, DoubleValue> articleGroup = new PropertyGroup<ArticleKey, DoubleValue>();
		PropertyGroup<StringKey, DoubleValue> termGroup = new PropertyGroup<StringKey, DoubleValue>();
		PropertyGroup<StringKey, DoubleValue> entityGroup = new PropertyGroup<StringKey, DoubleValue>();

		// build
		return new Topic(null, //
				title, //
				articleGroup, termGroup, entityGroup);
	}
}
