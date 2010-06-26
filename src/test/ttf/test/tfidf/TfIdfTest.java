package ttf.test.tfidf;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import ttf.analysis.tfidf.TfIdf;
import ttf.model.token.Token;
import ttf.util.tfidfapi.TfIdfEntity;

/**
 * 
 * Test for tfidf class.
 */
public class TfIdfTest {

	private final Log log = LogFactory.getLog(getClass());

	private final String uri = "http://www.mobilecrunch.com/2010/06/02/evo-4g-best-features-review/";

	@Test
	public void testTokenizing() throws Exception {
		TfIdf tfIdf = new TfIdf();
		int count = 0;
		Collection<Token> tokens = tfIdf.GetUrlTokens(uri);

		for (Token token : tokens) {
			count += token.getCount();
		}

		for (Token token : tokens) {
			double tf = (double) token.getCount() / (double) count;
			double idf = 1.00;
			// TODO compute idf
			TfIdfEntity entity = new TfIdfEntity(token, tf, idf);
			System.out.println(entity.toString());
		}

		System.out.println("Total count: " + count);
	}

}
