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
import ttf.model.property.HashMapPropertyGroup;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;

/**
 * Provides access to information pertaining to a story.
 * 
 * @author Mihai Paraschiv
 */
public class Article extends Model {
	private String address;
	private String title;
	private String author;
	private Date publishedAt;
	private Date discoveredAt;
	private String content;
	private Set<String> tags;
	private NumericalValue score;

	private Topic topic;

	private final PropertyGroup<String, NumericalValue> termGroup;
	private final PropertyGroup<String, NumericalValue> entityGroup;

	protected Article(String id) {
		super(id);
		termGroup = new HashMapPropertyGroup<String, NumericalValue>();
		entityGroup = new HashMapPropertyGroup<String, NumericalValue>();
	}

	protected Article() {
		this(null);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Date getDiscoveredAt() {
		return discoveredAt;
	}

	public void setDiscoveredAt(Date discoveredAt) {
		this.discoveredAt = discoveredAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NumericalValue getScore() {
		return score;
	}

	public void setScore(NumericalValue score) {
		this.score = score;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public PropertyGroup<String, NumericalValue> getTermGroup() {
		return termGroup;
	}

	public PropertyGroup<String, NumericalValue> getEntityGroup() {
		return entityGroup;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", address="
				+ address + ", content=" + content.substring(0, 20) + ", discoveredAt="
				+ discoveredAt + ", publishedAt=" + publishedAt + ", score="
				+ score + ", tags=" + tags + ", title=" + title + ", topic="
				+ topic + "]";
	}
}
