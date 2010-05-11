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
import ttf.model.ModelId;
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
	protected final Property<AddressValue> address;
	protected final Property<TextValue> title;
	protected final Property<TextValue> author;
	protected final Property<DateValue> publishedAt;
	protected final Property<DateValue> discoveredAt;
	protected final Property<TextValue> content;
	protected final Property<DoubleValue> score;
	protected final Property<TopicValue> topic;
	protected final Property<SetValue<String>> tags;

	protected final PropertyGroup<StringKey, DoubleValue> termGroup;
	protected final PropertyGroup<StringKey, DoubleValue> entityGroup;

	protected Article(ModelId id, Property<AddressValue> address,
			Property<TextValue> title, Property<TextValue> author,
			Property<DateValue> publishedAt, Property<DateValue> discoveredAt,
			Property<TextValue> content, Property<DoubleValue> score,
			Property<TopicValue> topic, Property<SetValue<String>> tags,
			PropertyGroup<StringKey, DoubleValue> termGroup,
			PropertyGroup<StringKey, DoubleValue> entityGroup) {
		super(id);
		this.address = address;
		this.title = title;
		this.author = author;
		this.publishedAt = publishedAt;
		this.discoveredAt = discoveredAt;
		this.content = content;
		this.score = score;
		this.topic = topic;
		this.tags = tags;
		this.termGroup = termGroup;
		this.entityGroup = entityGroup;
	}

	public Property<AddressValue> getAddress() {
		return address;
	}

	public Property<TextValue> getTitle() {
		return title;
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

	public PropertyGroup<StringKey, DoubleValue> getEntityGroup() {
		return entityGroup;
	}

	@Override
	public String toString() {
		return "Article [address=" + address + ", author=" + author
				+ ", content=" + content + ", discoveredAt=" + discoveredAt
				+ ", entityGroup=" + entityGroup + ", name=" + title
				+ ", publishedAt=" + publishedAt + ", score=" + score
				+ ", tags=" + tags + ", termGroup=" + termGroup + ", topic="
				+ topic + "]";
	}
}
