package ttf.model.property.value;

public class SimpleValue<V> implements PropertyValue {
	private final V value;

	public SimpleValue(V value) {
		this.value = value;
	}

	public V get() {
		return value;
	}

	@Override
	public String toString() {
		return "SimpleValue [value=" + value + "]";
	}
}
