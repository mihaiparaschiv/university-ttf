package ttf.model.store.property;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Iterators;

import ttf.model.store.property.key.PropertyKey;
import ttf.model.store.property.value.PropertyValue;

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
public class PropertyGroup<K extends PropertyKey<?>, V extends PropertyValue> {

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

	/**
	 * @return an iterator that does not allow element removal
	 */
	public Iterator<KeyedProperty<K, V>> getIterator() {
		return Iterators.unmodifiableIterator(map.values().iterator());
	}
}
