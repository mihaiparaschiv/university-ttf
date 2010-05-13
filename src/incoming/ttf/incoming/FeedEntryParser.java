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

import java.util.Date;
import java.util.List;

import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * This class builds {@link Article} objects from {@link SyndEntry}.
 * 
 * @author Mihai Paraschiv
 */
public class FeedEntryParser {
	private final ArticleFactory articleFactory;

	public FeedEntryParser(ArticleFactory articleFactory) {
		this.articleFactory = articleFactory;
	}

	public Article parse(SyndEntry entry) {
		Article article = articleFactory.build();

		article.setAddress(entry.getUri());
		article.setTitle(entry.getTitle());
		article.setAuthor(entry.getAuthor());
		article.setPublishedAt(entry.getPublishedDate());
		article.setDiscoveredAt(new Date());

		List<?> cList = entry.getContents();
		SyndContent c = null;
		if (cList.size() > 0) {
			c = (SyndContent) entry.getContents().get(0);
		} else {
			c = entry.getDescription();
		}
		article.setContent((c != null) ? c.getValue() : null);

		return article;
	}
}
