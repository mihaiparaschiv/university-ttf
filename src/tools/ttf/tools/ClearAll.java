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
package ttf.tools;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

import ttf.test.TestUtil;
import ttf.util.AppContext;

import com.sun.syndication.io.FeedException;

public class ClearAll {
	public static void main(String[] args) throws IllegalArgumentException,
			FeedException, IOException, ConfigurationException, SQLException {
		Configuration config = TestUtil.getDefaultConfiguration();
		AppContext appContext = AppContext.build(config);
		TestUtil.clearDatabase(appContext.getDataSource());
	}
}