/*
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
package ttf.analysis.command;

import java.util.Collection;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.context.AnalysisContext;
import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.util.tfidfapi.*;

/**
 * Detects TfIdf.
 * 
 */
public class TfIdfDetectionCommand implements Command {
	private final Log log = LogFactory.getLog(TfIdfDetectionCommand.class);

	@Override
	public boolean execute(Context context) throws Exception {
		AnalysisContext ctx = (AnalysisContext) context;
		
		Article article = ((AnalysisContext) context).getProcessedArticle();		
		String address = article.getAddress();

		TfIdfDetector detector = ctx.getTfIdfDetector();
		Collection<TfIdfEntity> entities = detector.getTfIdfForURL(address);

		PropertyGroup<String, NumericalValue> termGroup;
		termGroup = article.getTermGroup(); //only for topic...

		for (TfIdfEntity entity : entities) {
			String key = entity.getToken().getValue();
			NumericalValue value = new NumericalValue(entity.getTf() * entity.getIdf());
			termGroup.put(key, value);
		}
		
		log.debug("Found: " + termGroup.size() + " entities.");

		return false;
	}
}
