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

public class AlchemyEntity {
	private final String text;
	private final String type;
	private final double relevance;
	private final int count;

	public AlchemyEntity(String text, String type, double relevance, int count) {
		super();
		this.text = text;
		this.type = type;
		this.relevance = relevance;
		this.count = count;
	}

	public String getText() {
		return text;
	}

	public String getType() {
		return type;
	}

	public double getRelevance() {
		return relevance;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "AlchemyEntity [count=" + count + ", relevance=" + relevance
				+ ", text=" + text + ", type=" + type + "]";
	}
}
