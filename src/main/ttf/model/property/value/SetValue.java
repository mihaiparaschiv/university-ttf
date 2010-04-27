package ttf.model.property.value;

import java.util.Set;

import org.apache.commons.collections15.set.UnmodifiableSet;

public class SetValue<E> implements PropertyValue {
	private final Set<E> set;
	
	public SetValue(Set<E> set) {
		this.set = set;
	}
	
	public Set<E> get() {
		return UnmodifiableSet.decorate(set);
	}
}
