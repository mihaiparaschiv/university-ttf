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
import ttf.model.article.Article;
import ttf.model.property.HashMapPropertyGroup;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;

/**
 * A topic contains articles and subtopics.
 * 
 * @author Mihai Paraschiv
 */
public class Topic extends Model {
	protected String title;

	protected final PropertyGroup<Article, NumericalValue> articleGroup;
	protected final PropertyGroup<String, NumericalValue> termGroup;
	protected final PropertyGroup<String, NumericalValue> entityGroup;

	protected Topic(String id) {
		super(id);
		articleGroup = new HashMapPropertyGroup<Article, NumericalValue>();
		termGroup = new HashMapPropertyGroup<String, NumericalValue>();
		entityGroup = new HashMapPropertyGroup<String, NumericalValue>();
	}

	protected Topic() {
		this(null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PropertyGroup<Article, NumericalValue> getArticleGroup() {
		return articleGroup;
	}

	public PropertyGroup<String, NumericalValue> getTermGroup() {
		return termGroup;
	}

	public PropertyGroup<String, NumericalValue> getEntityGroup() {
		return entityGroup;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + "]";
	}
}
