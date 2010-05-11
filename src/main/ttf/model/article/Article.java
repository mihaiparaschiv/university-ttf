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

import ttf.model.Model;
import ttf.model.ModelId;
import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.value.NumericalValue;
import ttf.model.topic.Topic;

/**
 * Provides access to information pertaining to a story.
 * 
 * @author Mihai Paraschiv
 */
public class Article extends Model {
	protected final Property<String> address;
	protected final Property<String> title;
	protected final Property<String> author;
	protected final Property<Date> publishedAt;
	protected final Property<Date> discoveredAt;
	protected final Property<String> content;
	protected final Property<NumericalValue> score;
	protected final Property<Topic> topic;
	protected final Property<Set<String>> tags;

	protected final PropertyGroup<String, NumericalValue> termGroup;
	protected final PropertyGroup<String, NumericalValue> entityGroup;

	protected Article(ModelId id, Property<String> address,
			Property<String> title, Property<String> author,
			Property<Date> publishedAt, Property<Date> discoveredAt,
			Property<String> content, Property<NumericalValue> score,
			Property<Topic> topic, Property<Set<String>> tags,
			PropertyGroup<String, NumericalValue> termGroup,
			PropertyGroup<String, NumericalValue> entityGroup) {
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

	public Property<String> getAddress() {
		return address;
	}

	public Property<String> getTitle() {
		return title;
	}

	public Property<String> getAuthor() {
		return author;
	}

	public Property<Date> getPublishedAt() {
		return publishedAt;
	}

	public Property<Date> getDiscoveredAt() {
		return discoveredAt;
	}

	public Property<String> getContent() {
		return content;
	}

	public Property<NumericalValue> getScore() {
		return score;
	}

	public Property<Topic> getTopic() {
		return topic;
	}

	public Property<Set<String>> getTags() {
		return tags;
	}

	public PropertyGroup<String, NumericalValue> getTermGroup() {
		return termGroup;
	}

	public PropertyGroup<String, NumericalValue> getEntityGroup() {
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
