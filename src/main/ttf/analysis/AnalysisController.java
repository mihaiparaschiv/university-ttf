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
package ttf.analysis;

import ttf.analysis.input.ArticleProvider;
import ttf.analysis.processor.Processor;
import ttf.model.article.Article;

public class AnalysisController {
	private final ArticleProvider articleProvider;
	private final Processor processor;

	public AnalysisController(ArticleProvider articleProvider,
			Processor processor) {
		this.articleProvider = articleProvider;
		this.processor = processor;
	}

	public void run() {
		Article article = null;
		while ((article = articleProvider.poll()) != null) {
			try {
				processor.process(article);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
