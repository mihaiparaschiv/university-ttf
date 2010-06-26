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
package ttf.incoming;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class IncomingArticleListRSH implements
		ResultSetHandler<List<IncomingArticle>> {

	@Override
	public List<IncomingArticle> handle(ResultSet rs) throws SQLException {
		List<IncomingArticle> incomingArticles = new LinkedList<IncomingArticle>();

		while (rs.next()) {
			IncomingArticle incomingArticle = new IncomingArticle();
			incomingArticle.setTitle((String) rs.getObject(1));
			incomingArticle.setAuthor((String) rs.getObject(2));
			incomingArticle.setPublishedAt(new Date(((Timestamp) rs
					.getObject(3)).getTime()));
			incomingArticle.setDiscoveredAt(new Date(((Timestamp) rs
					.getObject(4)).getTime()));
			incomingArticle.setAddress((String) rs.getObject(5));
			incomingArticle.setContent((String) rs.getObject(6));

			incomingArticles.add(incomingArticle);
		}

		return incomingArticles;
	}

}
