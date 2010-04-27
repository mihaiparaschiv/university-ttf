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
package ttf.model.property.key;

public abstract class AbstractPropertyKey<K> implements PropertyKey {
	protected final K key;

	public AbstractPropertyKey(K key) {
		this.key = key;
	}
	
	public K get() {
		return key;
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractPropertyKey<?> other = (AbstractPropertyKey<?>) obj;
		return key.equals(other.key);
	}

	@Override
	public String toString() {
		return "AbstractPropertyKey [key=" + key + "]";
	}
}
