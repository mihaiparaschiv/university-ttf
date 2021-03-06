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
package ttf.util.alchemyapi;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ttf.util.XPathUtil;

import com.orchestr8.api.AlchemyAPI;

public class EntityDetector {
	private final AlchemyAPI alchemyAPI;

	private final XPathExpression xEntities;
	private final XPathExpression xText;
	private final XPathExpression xType;
	private final XPathExpression xRelevance;
	private final XPathExpression xCount;

	public EntityDetector(AlchemyAPI alchemyAPI) {
		this.alchemyAPI = alchemyAPI;

		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			xEntities = xpath.compile("//entity");
			xText = xpath.compile("text");
			xType = xpath.compile("type");
			xRelevance = xpath.compile("relevance");
			xCount = xpath.compile("count");
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	public Collection<AlchemyEntity> getEntitiesForURL(String url)
			throws XPathExpressionException, IOException, SAXException,
			ParserConfigurationException {
		Document doc = alchemyAPI.URLGetRankedNamedEntities(url);
		return extractEntities(doc);
	}

	public Collection<AlchemyEntity> getEntitiesForHTML(String html, String url)
			throws XPathExpressionException, IOException, SAXException,
			ParserConfigurationException {
		Document doc = alchemyAPI.HTMLGetRankedNamedEntities(html, url);
		return extractEntities(doc);
	}

	public Collection<AlchemyEntity> getEntitiesForText(String text, String url)
			throws XPathExpressionException, IOException, SAXException,
			ParserConfigurationException {
		Document doc = alchemyAPI.HTMLGetRankedNamedEntities(text, url);
		return extractEntities(doc);
	}

	private Collection<AlchemyEntity> extractEntities(Document doc)
			throws XPathExpressionException {
		Collection<AlchemyEntity> entities = new LinkedList<AlchemyEntity>();

		NodeList nodes = XPathUtil.getNodeList(doc, xEntities);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			AlchemyEntity entity = getEntity(node);
			entities.add(entity);
		}

		return entities;
	}

	private AlchemyEntity getEntity(Node node) throws XPathExpressionException {
		String text = XPathUtil.getString(node, xText);
		String type = XPathUtil.getString(node, xType);
		double relevance = XPathUtil.getDouble(node, xRelevance);
		int count = XPathUtil.getInt(node, xCount);

		return new AlchemyEntity(text, type, relevance, count);
	}
}
