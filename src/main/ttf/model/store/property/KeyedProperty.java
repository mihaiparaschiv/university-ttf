package ttf.model.store.property;

import ttf.model.store.property.key.PropertyKey;
import ttf.model.store.property.value.PropertyValue;

/**
 * This class is used with {@link PropertyGroup}.
 * 
 * @author Mihai Paraschiv
 * 
 * @param <K>
 *            property key, unique inside the property group
 * @param <V>
 *            property value
 */
public class KeyedProperty<K extends PropertyKey<?>, V extends PropertyValue> extends Property<V> {
	protected final K key;

	public KeyedProperty(K key, V value) {
		super(value);
		this.key = key;
	}

	public KeyedProperty(K key) {
		this(key, null);
	}
}
