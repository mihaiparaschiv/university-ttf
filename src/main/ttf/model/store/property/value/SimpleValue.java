package ttf.model.store.property.value;

public class SimpleValue<V> implements PropertyValue {
	private final V value;

	public SimpleValue(V value) {
		this.value = value;
	}

	public V get() {
		return value;
	}
}
