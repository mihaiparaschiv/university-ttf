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
import ttf.model.article.ArticleKey;
import ttf.model.property.PropertyGroup;
import ttf.model.property.value.DoubleValue;

/**
 * A topic contains articles and subtopics.
 * 
 * @author Mihai Paraschiv
 */
public class Topic extends Model<TopicId> {
	protected PropertyGroup<ArticleKey, DoubleValue> articles;
	protected PropertyGroup<TopicKey, DoubleValue> topics;

	protected Topic(TopicId id) {
		super(id);
		this.articles = new PropertyGroup<ArticleKey, DoubleValue>();
		this.topics = new PropertyGroup<TopicKey, DoubleValue>();
	}

	protected Topic() {
		this(null);
	}

	public PropertyGroup<ArticleKey, DoubleValue> getArticles() {
		return articles;
	}

	public PropertyGroup<TopicKey, DoubleValue> getTopics() {
		return topics;
	}
}
