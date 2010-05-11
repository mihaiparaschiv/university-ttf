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

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.BasicDataSource;

import com.orchestr8.api.AlchemyAPI;

/**
 * Singleton for application context. The instance has to be initialized.
 * 
 * @author Mihai Paraschiv
 * 
 */
public class AppContext {
	private static final String DEFAULT_CONFIG_FILE = "resources/base.properties";
	private static AppContext instance;

	private DataSource dataSource;
	private AlchemyAPI alchemyAPI;

	private AppContext(Configuration c) {
		// DataSource
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(c.getString("db.driverClassName"));
		dataSource.setUsername(c.getString("db.username"));
		dataSource.setPassword(c.getString("db.password"));
		dataSource.setUrl(c.getString("db.uri"));
		this.dataSource = dataSource;
		
		// Alchemy API
		String key = c.getString("alchemy.key");
		this.alchemyAPI = AlchemyAPI.GetInstanceFromString(key);
	}

	public static void setDefault() {
		try {
			setFromConfiguration(new PropertiesConfiguration(
					DEFAULT_CONFIG_FILE));
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setFromConfiguration(
			PropertiesConfiguration configuration) {
		if (instance != null) {
			throw new RuntimeException("AppContext already set!");
		}
		instance = new AppContext(configuration);
	}

	public static AppContext getInstance() {
		if (instance == null) {
			setDefault();
		}
		return instance;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public AlchemyAPI getAlchemyAPI() {
		return alchemyAPI;
	}
}