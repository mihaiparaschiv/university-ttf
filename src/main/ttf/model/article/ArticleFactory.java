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

import java.util.Date;
import java.util.Set;

import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.value.NumericalValue;
import ttf.model.topic.Topic;

public class ArticleFactory {
	public Article build() {
		// properties
		Property<String> address = new Property<String>(null);
		Property<String> title = new Property<String>(null);
		Property<String> author = new Property<String>(null);
		Property<Date> publishedAt = new Property<Date>(null);
		Property<Date> discoveredAt = new Property<Date>(null);
		Property<String> content = new Property<String>(null);
		Property<NumericalValue> score = new Property<NumericalValue>(null);
		Property<Set<String>> tags = new Property<Set<String>>(null);
		Property<Topic> topic = new Property<Topic>(null);

		// groups
		PropertyGroup<String, NumericalValue> termGroup = new PropertyGroup<String, NumericalValue>();
		PropertyGroup<String, NumericalValue> entityGroup = new PropertyGroup<String, NumericalValue>();

		// build
		return new Article(null, //
				address, title, author, publishedAt, //
				discoveredAt, content, score, topic, tags, //
				termGroup, entityGroup);
	}
}
