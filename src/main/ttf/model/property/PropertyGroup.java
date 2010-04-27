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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections15.collection.UnmodifiableCollection;

import ttf.model.property.key.PropertyKey;
import ttf.model.property.value.PropertyValue;

/**
 * Groups model properties.
 * 
 * @author Mihai Paraschiv
 * 
 * @param <K>
 *            the type of key managed by the store
 * @param <V>
 *            the type of value managed by the store
 */
public class PropertyGroup<K extends PropertyKey, V extends PropertyValue> {

	private final Map<K, KeyedProperty<K, V>> map;

	public PropertyGroup(Map<K, KeyedProperty<K, V>> map) {
		this.map = (map == null) ? new HashMap<K, KeyedProperty<K, V>>() : //
				new HashMap<K, KeyedProperty<K, V>>(map);
	}

	public PropertyGroup() {
		this(null);
	}

	public KeyedProperty<K, V> create(K key) {
		KeyedProperty<K, V> property = new KeyedProperty<K, V>(key);
		map.put(key, property);
		return property;
	}

	public KeyedProperty<K, V> get(K key) {
		return map.get(key);
	}

	public void remove(K key) {
		map.remove(key);
	}

	public boolean contains(K key) {
		return map.containsKey(key);
	}
	
	public Collection<KeyedProperty<K, V>> getProperties() {
		return UnmodifiableCollection.decorate(map.values());
	}
}
