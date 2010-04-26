package ttf.analysis;

import ttf.model.entry.Entry;

public interface EntryProvider {
	public Entry poll();
}
