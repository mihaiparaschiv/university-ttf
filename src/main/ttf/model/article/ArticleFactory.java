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
package ttf.model.article;

import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.key.StringKey;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.DoubleValue;
import ttf.model.property.value.SetValue;
import ttf.model.property.value.TextValue;
import ttf.model.topic.TopicValue;

public class ArticleFactory {
	public Article build() {
		// properties
		Property<AddressValue> address = new Property<AddressValue>(null);
		Property<TextValue> title = new Property<TextValue>(null);
		Property<TextValue> author = new Property<TextValue>(null);
		Property<DateValue> publishedAt = new Property<DateValue>(null);
		Property<DateValue> discoveredAt = new Property<DateValue>(null);
		Property<TextValue> content = new Property<TextValue>(null);
		Property<DoubleValue> score = new Property<DoubleValue>(null);
		Property<SetValue<String>> tags = new Property<SetValue<String>>(null);
		Property<TopicValue> topic = new Property<TopicValue>(null);

		// groups
		PropertyGroup<StringKey, DoubleValue> termGroup = new PropertyGroup<StringKey, DoubleValue>();
		PropertyGroup<StringKey, DoubleValue> entityGroup = new PropertyGroup<StringKey, DoubleValue>();

		// build
		return new Article(null, //
				address, title, author, publishedAt, //
				discoveredAt, content, score, topic, tags, //
				termGroup, entityGroup);
	}
}
