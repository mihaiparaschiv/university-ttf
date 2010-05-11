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
package ttf.model.property;

/**
 * An attribute of a model. Properties can be attached directly or they can be
 * bundled in a {@link PropertyGroup}.
 * 
 * The purpose of this class is to allow complex model manipulation by
 * separating the model class from the persistency layer. An example of
 * mechanism that can be built on top of this class is the lazy loading of model
 * attributes.
 * 
 * @author Mihai Paraschiv
 * 
 * @param <V>
 *            property value
 */
public class Property<V> {
	protected V value;

	public Property(V value) {
		this.value = value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Property [value=" + value + "]";
	}
}
