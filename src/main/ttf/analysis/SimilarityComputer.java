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
package ttf.analysis;

import java.util.Map.Entry;

import ttf.model.article.Article;
import ttf.model.property.NumericalValue;
import ttf.model.property.PropertyGroup;
import ttf.model.topic.Topic;

/**
 * This class will be changed in order to use a custom set of metrics.
 * 
 * @author Mihai Paraschiv
 * 
 */
public class SimilarityComputer {
	public double compute(Article article, Topic topic) {
		double result = 0;

		result += computeEntitySimilarity(article, topic);

		return result;
	}

	private double computeEntitySimilarity(Article article, Topic topic) {
		PropertyGroup<String, NumericalValue> groupA = article.getEntityGroup();
		PropertyGroup<String, NumericalValue> groupB = topic.getEntityGroup();

		double result = 0;

		// compute the scalar product
		for (Entry<String, NumericalValue> entryA : groupA.entrySet()) {
			double vA = entryA.getValue().getDouble();
			double vB = groupB.get(entryA.getKey()).getDouble();
			result += vA * vB;
		}

		// compute the norms - will be cached
		double normA = computeNorm(groupA);
		double normB = computeNorm(groupB);

		// compute the cosine similarity
		result /= normA * normB;

		return result;
	}

	private double computeNorm(PropertyGroup<String, NumericalValue> group) {
		double norm = 0;
		for (NumericalValue value : group.values()) {
			double v = value.getDouble();
			norm += v * v;
		}
		return norm;
	}
}
