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
package ttf.util;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.NodeList;

public class XPathUtil {
	public static NodeList getNodeList(Object node, XPathExpression expression)
			throws XPathExpressionException {
		return (NodeList) expression.evaluate(node, XPathConstants.NODESET);
	}

	public static String getString(Object node, XPathExpression expression)
			throws XPathExpressionException {
		return (String) expression.evaluate(node, XPathConstants.STRING);
	}

	public static double getDouble(Object node, XPathExpression expression)
			throws XPathExpressionException {
		return ((Double) expression.evaluate(node, XPathConstants.NUMBER))
				.doubleValue();
	}

	public static int getInt(Object node, XPathExpression expression)
			throws XPathExpressionException {
		return ((Double) expression.evaluate(node, XPathConstants.NUMBER))
				.intValue();
	}
}
