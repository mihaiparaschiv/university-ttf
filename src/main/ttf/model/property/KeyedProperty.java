package ttf.model.property;

import ttf.model.property.key.PropertyKey;
import ttf.model.property.value.PropertyValue;

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
public class KeyedProperty<K extends PropertyKey, V extends PropertyValue> extends Property<V> {
	protected final K key;

	public KeyedProperty(K key, V value) {
		super(value);
		this.key = key;
	}

	public KeyedProperty(K key) {
		this(key, null);
	}

	@Override
	public String toString() {
		return "KeyedProperty [key=" + key + ", value=" + value + "]";
	}
}
