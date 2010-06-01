package ttf.test.tokenizer;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import ttf.model.token.*;
import ttf.tfidf.tokenizer.*;

/**
 * 
 * Test for tokenizer.
 */
public class TokenizerTest {

  private final Log log = LogFactory.getLog(getClass());
  
  private String[] INPUT_TEXTS = {
    "The growing popularity of Linux in Asia, Europe, and the U.S. is a major concern " +
    "for Microsoft. It costs less than 1 USD a month to maintain a Linux PC in Asia. " +
    "By 2007, over 500,000 PCs sold in Asia may be Linux based.",
    "Jaguar will sell its new XJ-6 model in the U.S. for a small fortune :( " +
    "(http://www.jaguar.com/sales or contact xj-6@jaguar.com).",
    "Argentine officials said the country needed \"a more serious climate\" to carry " +
    "out structured changes in the economy.",
    "They know that controlling inflation will make the industry more competitive and " +
    "bring down unemployment in the long run.",
    "OTTOWA, March 3 -- Canada's real gross domestic product, seasonally adjusted, " +
    "rose 1.1 pct in the fourth quarter of 1986, the same as the growth as in the " +
    "previous quarter, Statistics Canada said. That left growth for the full year at " +
    "3 pct, which is down from 1985's four pct increase. The rise was also slightly " +
    "below the 3.3 pct growth rate Finance Minister Michael Wilson predicted for 1986 " +
    "in February's budget. He also forecast GDP would rise 2.8 pct in 1987. Statistics " +
    "Canada said final domestic demand rose 0.6 pct in the final three months of the " +
    "year after a 1.0% gain in the third quarter.",
    "I have a <a href=\"http://www.funny.com/funnyurl\">funny url</a> to share.",
    "The problem occured at 3:00am in the morning on www.ibm.co.in because of a request from 192.168.1.119."
  };
  
 // @Test
  public void testTokenizing() throws Exception {
    SentenceTokenizer sentenceTokenizer = new SentenceTokenizer();
    WordTokenizer wordTokenizer = new WordTokenizer();
    BasicTokenFactory basicTokenFactory = new BasicTokenFactory();
    for (String inputText : INPUT_TEXTS) {
      sentenceTokenizer.setText(inputText);
      String sentence = null;
      while ((sentence = sentenceTokenizer.nextSentence()) != null) {
        System.out.println("sentence=" + sentence);
        wordTokenizer.setText(sentence);
        Token token = null;
        while ((token = wordTokenizer.nextToken()) != null) {
          System.out.println("token=" + token.toString());
        }
      }
    }
  }
  
  // @Test
  public void testTokenizingForExamplePara() throws Exception {
    String paragraph = "Jaguar will sell its new XJ-6 model in the U.S. for " +
      "a small fortune :-). Expect to pony up around USD 120ks. Custom options can " +
      "set you back another few 10,000 dollars. For details, go to " +
      "<a href=\"http://www.jaguar.com/sales\" alt=\"Click here\">Jaguar Sales</a> " +
      "or contact xj-6@jaguar.com.";
    SentenceTokenizer sentenceTokenizer = new SentenceTokenizer();
    WordTokenizer wordTokenizer = new WordTokenizer();
    sentenceTokenizer.setText(paragraph);
    String sentence = null;
    while ((sentence = sentenceTokenizer.nextSentence()) != null) {
//      log.debug("sentence=[" + sentence + "]");
      System.out.println("sentence=[" + sentence + "]");
      wordTokenizer.setText(sentence);
      Token token = null;
      while ((token = wordTokenizer.nextToken()) != null) {
//        log.debug("token=" + token.getValue() + " [" + token.getType() + "]");
        System.out.println("token=" + token.getValue() + " [" + token.getType() + "]");
      }
    }
  }
  
  @Test
  public void testTokenizeWebPageTextSentences() throws Exception {
    SentenceTokenizer sentenceTokenizer = new SentenceTokenizer();
    sentenceTokenizer.setText(FileUtils.readFileToString(
      new File("resources/cocoa.txt"), "UTF-8"));
    String sentence = null;
    while ((sentence = sentenceTokenizer.nextSentence()) != null) {
      System.out.println(">>> sentence: " + sentence);
    }
  }
  
  // @Test
  public void testTokenizeWebPageTextParagraphs() throws Exception {
    ParagraphTokenizer paragraphTokenizer = new ParagraphTokenizer();
    paragraphTokenizer.setText(FileUtils.readFileToString(
      new File("resources/cocoa.txt"), "UTF-8"));
    String paragraph = null;
    while ((paragraph = paragraphTokenizer.nextParagraph()) != null) {
      System.out.println(">>> paragraph: " + paragraph);
    }
  }

}
