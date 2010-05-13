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
package ttf.test;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import ttf.util.AppContext;
import ttf.util.alchemyapi.AlchemyEntity;
import ttf.util.alchemyapi.EntityDetector;

import com.orchestr8.api.AlchemyAPI;

public class AlchemyAPITest {
	private static final String TEST_URL = "http://techcrunch.com";
	private EntityDetector entityDetector;

	@Before
	public void initialize() throws ConfigurationException,
			XPathExpressionException {
		AlchemyAPI alchemyAPI = AppContext.getInstance().getAlchemyAPI();
		entityDetector = new EntityDetector(alchemyAPI);
	}

	@Test
	public void namedEntityDetection() throws XPathExpressionException,
			IOException, SAXException, ParserConfigurationException {
		Collection<AlchemyEntity> es = entityDetector
				.getEntitiesForURL(TEST_URL);
		System.out.println(es);
	}
}
