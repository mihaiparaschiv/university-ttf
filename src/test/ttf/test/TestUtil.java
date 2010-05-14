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

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbutils.QueryRunner;

public class TestUtil {
	private static final String CONFIG_FILE = "test.properties";

	public static Configuration getDefaultConfiguration() {
		try {
			return new PropertiesConfiguration(CONFIG_FILE);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static void clearDatabase(DataSource dataSource) {
		String[] tables = { "IncomingArticles", "Sources", "Topics",
				"TopicFeatures", "Articles", "ArticleFeatures" };
		
		QueryRunner run = new QueryRunner(dataSource);
		try {
			for (String table : tables) {
				run.update("DELETE FROM " + table);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
