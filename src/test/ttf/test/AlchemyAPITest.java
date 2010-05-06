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
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import ttf.ConfigurationNames;
import ttf.alchemyapi.AlchemyEntity;
import ttf.alchemyapi.EntityProvider;

import com.orchestr8.api.AlchemyAPI;

public class AlchemyAPITest {
	private static final String CONFIG_FILE = "files/config.ini";
	private static final String TEST_URL = "http://techcrunch.com";
	
	private HierarchicalINIConfiguration config;
	private AlchemyAPI alchemyAPI;

	@Before
	public void initialize() throws ConfigurationException {
		config = new HierarchicalINIConfiguration(CONFIG_FILE);
		String key = config.getSection(ConfigurationNames.ALCHEMY_API)
				.getString(ConfigurationNames.ALCHEMY_API_KEY);
		alchemyAPI = AlchemyAPI.GetInstanceFromString(key);
	}

	@Test
	public void namedEntities() throws XPathExpressionException, IOException,
			SAXException, ParserConfigurationException {
		EntityProvider provider = new EntityProvider(alchemyAPI);
		Collection<AlchemyEntity> es = provider.getEntitiesForURL(TEST_URL);
		System.out.println(es);
	}
}
