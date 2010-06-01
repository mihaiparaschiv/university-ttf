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
package ttf.util.tfidfapi;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import ttf.tfidf.TfIdf;
import ttf.model.token.*;


public class TfIdfDetector {

	private TfIdf tfIdf;
	public TfIdfDetector(TfIdf tfIdf) {
		this.tfIdf = tfIdf;
	}

	public Collection<TfIdfEntity> getTfIdfForURL(String url) throws Exception
	{
		Collection<TfIdfEntity> entities = new LinkedList<TfIdfEntity>();
		Collection<Token> tokens = tfIdf.GetUrlTokens(url);
		
		for (Token token : tokens) {
			double tf = 0; //compute tf
			// TODO compute tf
			double idf = 0; //and idf
			// TODO compute idf
			TfIdfEntity entity = new TfIdfEntity(token, tf, idf);
			entities.add(entity);
		}
				
		return entities;
	}
}
