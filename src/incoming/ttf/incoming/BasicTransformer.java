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
package ttf.incoming;

import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;

public class BasicTransformer implements Transformer {
	private final ArticleFactory articleFactory;

	public BasicTransformer(ArticleFactory articleFactory) {
		this.articleFactory = articleFactory;
	}

	@Override
	public Article transform(IncomingArticle incoming) {
		Article article = articleFactory.build();

		article.setAddress(incoming.getAddress());
		article.setTitle(incoming.getTitle());
		article.setAuthor(incoming.getAuthor());
		article.setPublishedAt(incoming.getPublishedAt());
		article.setDiscoveredAt(incoming.getDiscoveredAt());
		article.setContent(incoming.getContent());
		article.setTags(incoming.getTags());

		return article;
	}
}
