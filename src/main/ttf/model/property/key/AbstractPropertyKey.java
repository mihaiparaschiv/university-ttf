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
