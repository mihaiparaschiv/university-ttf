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

import ttf.model.Model;
import ttf.model.StringId;
import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.key.StringKey;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.DoubleValue;
import ttf.model.property.value.SetValue;
import ttf.model.property.value.TextValue;
import ttf.model.topic.TopicValue;

/**
 * Provides access to information pertaining to a story.
 * 
 * @author Mihai Paraschiv
 */
public class Article extends Model {
	protected Property<AddressValue> address;
	protected Property<TextValue> name;
	protected Property<TextValue> author;
	protected Property<DateValue> publishedAt;
	protected Property<DateValue> discoveredAt;
	protected Property<TextValue> content;
	protected Property<DoubleValue> score;
	protected Property<TopicValue> topic;
	protected Property<SetValue<String>> tags;

	private PropertyGroup<StringKey, DoubleValue> termGroup;

	protected Article(StringId id) {
		super(id);
		address = new Property<AddressValue>(null);
		name = new Property<TextValue>(null);
		author = new Property<TextValue>(null);
		publishedAt = new Property<DateValue>(null);
		discoveredAt = new Property<DateValue>(null);
		content = new Property<TextValue>(null);
		score = new Property<DoubleValue>(null);
		tags = new Property<SetValue<String>>(null);
		topic = new Property<TopicValue>(null);
	}

	protected Article() {
		this(null);
	}

	public Property<AddressValue> getAddress() {
		return address;
	}

	public Property<TextValue> getName() {
		return name;
	}

	public Property<TextValue> getAuthor() {
		return author;
	}

	public Property<DateValue> getPublishedAt() {
		return publishedAt;
	}

	public Property<DateValue> getDiscoveredAt() {
		return discoveredAt;
	}

	public Property<TextValue> getContent() {
		return content;
	}

	public Property<DoubleValue> getScore() {
		return score;
	}

	public Property<TopicValue> getTopic() {
		return topic;
	}

	public Property<SetValue<String>> getTags() {
		return tags;
	}

	public PropertyGroup<StringKey, DoubleValue> getTermGroup() {
		return termGroup;
	}

	@Override
	public String toString() {
		return "Article [address=" + address + ", author=" + author
				+ ", content=" + content + ", discoveredAt=" + discoveredAt
				+ ", name=" + name + ", publishedAt=" + publishedAt
				+ ", score=" + score + ", tags=" + tags + ", termGroup="
				+ termGroup + ", topic=" + topic + "]";
	}
}
