package ttf.model.property.key;

import ttf.model.property.PropertyGroup;

/**
 * A key specifies how the property's unique name in a {@link PropertyGroup}.
 * 
 * @author Mihai Paraschiv
 */
public class PropertyKey<K> {
	protected final K key;

	public PropertyKey(K key) {
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
		PropertyKey<?> other = (PropertyKey<?>) obj;
		return key.equals(other.key);
	}
}
