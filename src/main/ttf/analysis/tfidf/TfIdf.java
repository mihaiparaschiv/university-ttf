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
package ttf.analysis.tfidf;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ttf.analysis.tfidf.tokenizer.SentenceTokenizer;
import ttf.analysis.tfidf.tokenizer.WordTokenizer;
import ttf.model.token.BasicTokenFactory;
import ttf.model.token.Token;
import ttf.util.tfidfapi.TfIdfEntity;

public class TfIdf {
	public TfIdf() {
	}

	private Document GetUrlDocument(String uri) throws IOException,
			SAXException, ParserConfigurationException {
		URL url = new URL(uri);
		HttpURLConnection handle = (HttpURLConnection) url.openConnection();
		handle.setDoOutput(true);

		DataInputStream istream = new DataInputStream(handle.getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(istream);

		istream.close();
		handle.disconnect();

		return doc;
	}

	public Collection<Token> GetUrlTokens(String url) throws Exception {
		Collection<Token> tokens = new LinkedList<Token>();

		Document doc = GetUrlDocument(url);
		// parse doc ...

		SentenceTokenizer sentenceTokenizer = new SentenceTokenizer();
		WordTokenizer wordTokenizer = new WordTokenizer();

		sentenceTokenizer.setText(doc.toString());
		// TODO maybe a good doc parse would be better
		String sentence = null;
		while ((sentence = sentenceTokenizer.nextSentence()) != null) {
			System.out.println("sentence=" + sentence);
			wordTokenizer.setText(sentence);
			Token token = null;
			while ((token = wordTokenizer.nextToken()) != null) {
				System.out.println("token=" + token.toString());
				tokens.add(token);
			}
		}

		return tokens;
	}
}
