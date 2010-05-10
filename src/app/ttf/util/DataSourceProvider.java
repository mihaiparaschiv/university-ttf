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
import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceProvider {
	private static BasicDataSource dataSource;

	public static DataSource getDefault() {
		if (dataSource == null) {
			Configuration c = ConfigurationProvider.getDefault();
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(c.getString("db.driverClassName"));
			dataSource.setUsername(c.getString("db.username"));
			dataSource.setPassword(c.getString("db.password"));
			dataSource.setUrl(c.getString("db.uri"));
		}
		return dataSource;
	}
}
