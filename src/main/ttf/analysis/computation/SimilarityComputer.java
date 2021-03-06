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
package ttf.analysis.computation;

import java.util.Map.Entry;

import ttf.analysis.context.AnalysisContext;
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
	public double compute(Article article, Topic topic, AnalysisContext context) {
		double result = 0;

		result += computeEntitySimilarity(article, topic);
		result += computeTfIdfSimilarity(article, topic, context);

		return result;
	}

	private double computeEntitySimilarity(Article article, Topic topic) {
		PropertyGroup<String, NumericalValue> groupA = article.getEntityGroup();
		PropertyGroup<String, NumericalValue> groupB = topic.getEntityGroup();

		double result = 0;

		// compute the scalar product
		for (Entry<String, NumericalValue> entryA : groupA.entrySet()) {
			String key = entryA.getKey();
			NumericalValue nvA = entryA.getValue();
			NumericalValue nvB = groupB.get(key);
			if (nvB != null) {
				double vA = nvA.getDouble();
				double vB = nvB.getDouble();
				result += vA * vB;
			}
		}

		if (result == 0) {
			return 0;
		}

		// compute the norms - should be cached
		double normA = computeNorm(groupA);
		double normB = computeNorm(groupB);

		// compute the cosine similarity
		result /= Math.sqrt(normA * normB);

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
	
	private double computeTfIdfSimilarity(Article article, Topic topic,
			AnalysisContext context) {
		// TODO Auto-generated method stub
		return 0;
	}
}
