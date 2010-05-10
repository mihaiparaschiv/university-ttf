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

import static junit.framework.Assert.assertEquals;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.junit.Test;

public class ConfigurationTest {
	private static final String CONFIG_FILE = "resources/test-config.ini";
	private static final String TEST_NAME = "test-variable";
	private static final int TEST_VALUE = 1;

	@Test
	public void configurationLoadingFromFile()
			throws ConfigurationException {
		Configuration config = new HierarchicalINIConfiguration(CONFIG_FILE);
		assertEquals(TEST_VALUE, config.getInt(TEST_NAME));
	}
}
