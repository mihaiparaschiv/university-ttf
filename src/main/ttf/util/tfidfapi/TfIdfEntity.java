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

import ttf.model.token.*;

public class TfIdfEntity {
	private final Token token;
	private final double tf;
	private final double idf;

	public TfIdfEntity(Token token, double tf, double idf) {
		super();
		this.token = token;
		this.tf = tf;
		this.idf = idf;
	}

	public Token getToken() {
		return token;
	}

	public double getTf() {
		return tf;
	}

	public double getIdf() {
		return idf;
	}

	@Override
	public String toString() {
		return "[Token = " + token.getValue() + " " + "tf=" + tf
		+ " " + "idf=" + idf + "]";
	}
}
