package com.orchestr8.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class AlchemyAPI {

	private String _apiKey;
	private String _requestUri = "http://access.alchemyapi.com/calls/";

	private AlchemyAPI() {
	}

	static public AlchemyAPI GetInstanceFromFile(String keyFilename)
			throws FileNotFoundException, IOException {
		AlchemyAPI api = new AlchemyAPI();
		api.LoadAPIKey(keyFilename);

		return api;
	}

	static public AlchemyAPI GetInstanceFromString(String apiKey) {
		AlchemyAPI api = new AlchemyAPI();
		api.SetAPIKey(apiKey);

		return api;
	}

	public void LoadAPIKey(String filename) throws IOException,
			FileNotFoundException {
		if (null == filename || 0 == filename.length())
			throw new IllegalArgumentException("Empty API key file specified.");

		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);

		BufferedReader breader = new BufferedReader(new InputStreamReader(fis));

		_apiKey = breader.readLine().replaceAll("\\n", "")
				.replaceAll("\\r", "");

		fis.close();
		breader.close();

		if (null == _apiKey || _apiKey.length() < 5)
			throw new IllegalArgumentException("Too short API key.");
	}

	public void SetAPIKey(String apiKey) {
		_apiKey = apiKey;

		if (null == _apiKey || _apiKey.length() < 5)
			throw new IllegalArgumentException("Too short API key.");
	}

	public void SetAPIHost(String apiHost) {
		if (null == apiHost || apiHost.length() < 2)
			throw new IllegalArgumentException("Too short API host.");

		_requestUri = "http://" + apiHost + ".alchemyapi.com/calls/";
	}

	public Document URLGetRankedNamedEntities(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetRankedNamedEntities", "url", "url", url);
	}

	public Document HTMLGetRankedNamedEntities(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetRankedNamedEntities", "html", "html", html, "url",
				url);
	}

	public Document TextGetRankedNamedEntities(String text) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckText(text);

		return POST("TextGetRankedNamedEntities", "text", "text", text);
	}

	public Document URLGetRankedKeywords(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetRankedKeywords", "url", "url", url);
	}

	public Document HTMLGetRankedKeywords(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetRankedKeywords", "html", "html", html, "url", url);
	}

	public Document TextGetRankedKeywords(String text) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckText(text);

		return POST("TextGetRankedKeywords", "text", "text", text);
	}

	public Document URLGetLanguage(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetLanguage", "url", "url", url);
	}

	public Document HTMLGetLanguage(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetLanguage", "html", "html", html, "url", url);
	}

	public Document TextGetLanguage(String text) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckText(text);

		return POST("TextGetLanguage", "text", "text", text);
	}

	public Document URLGetCategory(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetCategory", "url", "url", url);
	}

	public Document HTMLGetCategory(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetCategory", "html", "html", html, "url", url);
	}

	public Document TextGetCategory(String text) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckText(text);

		return POST("TextGetCategory", "text", "text", text);
	}

	public Document URLGetText(String url) throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException {
		CheckURL(url);

		return GET("URLGetText", "url", "url", url);
	}

	public Document HTMLGetText(String html, String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetText", "html", "html", html, "url", url);
	}

	public Document URLGetRawText(String url) throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException {
		CheckURL(url);

		return GET("URLGetRawText", "url", "url", url);
	}

	public Document HTMLGetRawText(String html, String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetRawText", "html", "html", html, "url", url);
	}

	public Document URLGetTitle(String url) throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException {
		CheckURL(url);

		return GET("URLGetTitle", "url", "url", url);
	}

	public Document HTMLGetTitle(String html, String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetTitle", "html", "html", html, "url", url);
	}

	public Document URLGetFeedLinks(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetFeedLinks", "url", "url", url);
	}

	public Document HTMLGetFeedLinks(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetFeedLinks", "html", "html", html, "url", url);
	}

	public Document URLGetMicroformats(String url) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckURL(url);

		return GET("URLGetMicroformatData", "url", "url", url);
	}

	public Document HTMLGetMicroformats(String html, String url)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		CheckHTML(html, url);

		return POST("HTMLGetMicroformatData", "html", "html", html, "url", url);
	}

	public Document URLGetConstraintQuery(String url, String query)
			throws IOException, XPathExpressionException, SAXException,
			ParserConfigurationException {
		CheckURL(url);
		if (null == query || query.length() < 2)
			throw new IllegalArgumentException(
					"Invalid constraint query specified");

		return POST("URLGetConstraintQuery", "url", "url", url, "cquery", query);
	}

	public Document HTMLGetConstraintQuery(String html, String url, String query)
			throws IOException, XPathExpressionException, SAXException,
			ParserConfigurationException {
		CheckHTML(html, url);
		if (null == query || query.length() < 2)
			throw new IllegalArgumentException(
					"Invalid constraint query specified");

		return POST("HTMLGetConstraintQuery", "html", "html", html, "url", url,
				"cquery", query);
	}

	private void CheckHTML(String html, String url) {
		if (null == html || html.length() < 5)
			throw new IllegalArgumentException(
					"Enter a HTML document to analyze.");

		if (null == url || url.length() < 10)
			throw new IllegalArgumentException("Enter an URL to analyze.");
	}

	private void CheckText(String text) {
		if (null == text || text.length() < 5)
			throw new IllegalArgumentException("Enter some text to analyze.");
	}

	private void CheckURL(String url) {
		if (null == url || url.length() < 10)
			throw new IllegalArgumentException("Enter an URL to analyze.");
	}

	private Document GET(String callName, String callPrefix, String... param)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		StringBuilder uri = new StringBuilder();
		uri.append(_requestUri).append(callPrefix).append('/').append(callName)
				.append('?').append("apikey=").append(this._apiKey).append(
						"&outputMode=xml");

		for (int i = 0; i < param.length; ++i) {
			uri.append('&').append(param[i]);
			if (++i < param.length)
				uri.append('=').append(URLEncoder.encode(param[i], "UTF8"));
		}

		URL url = new URL(uri.toString());
		HttpURLConnection handle = (HttpURLConnection) url.openConnection();
		handle.setDoOutput(true);

		return doRequest(handle);
	}

	private Document POST(String callName, String callPrefix, String... param)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		URL url = new URL(_requestUri + callPrefix + "/" + callName);

		HttpURLConnection handle = (HttpURLConnection) url.openConnection();
		handle.setDoOutput(true);

		StringBuilder data = new StringBuilder();

		data.append("apikey=").append(this._apiKey).append("&outputMode=xml");
		for (int i = 0; i < param.length; ++i) {
			data.append('&').append(param[i]);
			if (++i < param.length)
				data.append('=').append(URLEncoder.encode(param[i], "UTF8"));
		}

		handle.addRequestProperty("Content-Length", Integer.toString(data
				.length()));

		DataOutputStream ostream = new DataOutputStream(handle
				.getOutputStream());
		ostream.write(data.toString().getBytes());
		ostream.close();

		return doRequest(handle);
	}

	private Document doRequest(HttpURLConnection handle) throws IOException,
			SAXException, ParserConfigurationException,
			XPathExpressionException {
		DataInputStream istream = new DataInputStream(handle.getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(istream);

		istream.close();
		handle.disconnect();

		XPathFactory factory = XPathFactory.newInstance();

		String statusStr = getNodeValue(factory, doc, "/results/status/text()");
		if (null == statusStr || !statusStr.equals("OK")) {
			String statusInfoStr = getNodeValue(factory, doc,
					"/results/statusInfo/text()");
			if (null != statusInfoStr && statusInfoStr.length() > 0)
				throw new IOException("Error making API call: " + statusInfoStr
						+ '.');

			throw new IOException("Error making API call: " + statusStr + '.');
		}

		return doc;
	}

	private String getNodeValue(XPathFactory factory, Document doc,
			String xpathStr) throws XPathExpressionException {
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(xpathStr);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList results = (NodeList) result;

		if (results.getLength() > 0 && null != results.item(0))
			return results.item(0).getNodeValue();

		return null;
	}
}
