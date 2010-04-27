package ttf.model.property;

import ttf.model.property.value.PropertyValue;

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
public class Property<V extends PropertyValue> {
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
}
