package ttf.model.store.property.value;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Iterators;

public class SetValue<E> implements PropertyValue, Iterable<E> {
	private final Set<E> set;
	
	public SetValue(Set<E> set) {
		this.set = set;
	}

	public boolean contains(Object o) {
		return set.contains(o);
	}

	/**
	 * Returns an iterator that does not allow element removal.
	 */
	public Iterator<E> iterator() {
		return Iterators.unmodifiableIterator(set.iterator());
	}

	public int size() {
		return set.size();
	}
}
