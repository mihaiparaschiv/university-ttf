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
package ttf.test.analysis;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

import ttf.analysis.AnalysisController;
import ttf.analysis.input.InternalProvider;
import ttf.analysis.processor.Processor;
import ttf.model.article.Article;
import ttf.model.article.ArticleFactory;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.TextValue;
import ttf.util.AppContext;

public class DummyTest {
	private static final String NEW_TITLE = "new title";

	@Test
	public void dummyTaskExecution() {
		ArticleFactory factory = AppContext.getInstance().getArticleFactory();
		Article article = factory.build();
		article.getAddress().setValue(new AddressValue("http://..."));
		article.getTitle().setValue(new TextValue("A news article"));
		article.getContent().setValue(new TextValue("News content"));
		article.getDiscoveredAt().setValue(new DateValue(new Date()));

		InternalProvider articleProvider = new InternalProvider();
		articleProvider.add(article);
		Processor processor = new DummyProcessor();
		AnalysisController controller = new AnalysisController(articleProvider,
				processor);
		controller.run();
		assertEquals(NEW_TITLE, article.getTitle().getValue().get());
	}

	private class DummyCommand implements Command {
		@Override
		public boolean execute(Context context) {
			Article article = ((DummyContext) context).getArticle();
			System.out.println(article);
			article.getTitle().setValue(new TextValue(NEW_TITLE));
			return false;
		}
	}

	private class DummyContext extends ContextBase {
		private static final long serialVersionUID = -651154027320406482L;
		private Article article;

		public void setArticle(Article article) {
			this.article = article;
		}

		public Article getArticle() {
			return article;
		}
	}

	private class DummyProcessor implements Processor {
		@Override
		public void process(Article article) {
			DummyContext context = new DummyContext();
			context.setArticle(article);
			DummyCommand command = new DummyCommand();
			command.execute(context);
		}
	}
}
