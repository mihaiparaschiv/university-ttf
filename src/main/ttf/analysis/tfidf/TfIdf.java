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


import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import ttf.analysis.tfidf.tokenizer.SentenceTokenizer;
import ttf.analysis.tfidf.tokenizer.WordTokenizer;
import ttf.model.token.Token;
import ttf.model.token.TokenType;

import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.StopFilter;
import org.htmlparser.parserapplications.StringExtractor;

public class TfIdf {
	
	private final int MaxWordCount = 24;//eliminates unwanted tokens  
	public TfIdf() {
		SummaryAnalyzer(); // make the stopwords set
	}

	private Set<String> stopset;

	public void SummaryAnalyzer(){

		String[] stopwords = null;
		try
		{
			stopwords = filterComments(StringUtils.split(
					FileUtils.readFileToString(new File(
					"resources/stopwords.txt"), "UTF-8")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		if (stopwords != null)
			this.stopset = StopFilter.makeStopSet(stopwords, true);
	}		

	private String[] filterComments(String[] input) {
		List<String> stopwords = new ArrayList<String>();
		for (String stopword : input) {
			if (! stopword.startsWith("#")) {
				stopwords.add(stopword);
			}
		}
		return stopwords.toArray(new String[0]);
	}

	public Collection<Token> GetUrlTokens(String uri) throws Exception {

		Collection<Token> tokens = new LinkedList<Token>();
		HashMap <String, Token> TokenOccurences = new HashMap<String, Token>();

		SentenceTokenizer sentenceTokenizer = new SentenceTokenizer();
		WordTokenizer wordTokenizer = new WordTokenizer();
		
		StringExtractor se = new StringExtractor (uri); // uses HTML parser - StringExtractor
		sentenceTokenizer.setText(se.extractStrings (false));
		String sentence = null;
		while ((sentence = sentenceTokenizer.nextSentence()) != null) {
			//System.out.println("sentence=" + sentence);
			wordTokenizer.setText(sentence);
			Token token = null;
			while ((token = wordTokenizer.nextToken()) != null) {
				//System.out.println("token=" + token.toString());
				if (filterToken(token))
				{
					if (TokenOccurences.containsKey(token.getValue()))
					{						
						token = TokenOccurences.get(token.getValue());
						token.incrementCount();
					}
					else		
					{						
						TokenOccurences.put(token.getValue(), token);
						tokens.add(token);
					}				
				}
			}	
		}
		return tokens;
	}

	private Boolean filterToken(Token token)
	{
		if (token.getType() != TokenType.WORD /*&&
				token.getType() != TokenType.NUMBER*/
		)
			return false;

		if (stopset.contains(StringUtils.lowerCase(token.getValue())))	 // is a stop word					
			return false;
		
		if (token.getValue().length() > MaxWordCount)
			return false;

		return true;
	}



}
