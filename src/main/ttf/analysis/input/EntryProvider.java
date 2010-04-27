package ttf.analysis.input;

import ttf.model.entry.Entry;

/**
 * Provides new entries.
 * 
 * @author Mihai Paraschiv
 * 
 */
public interface EntryProvider {
	public Entry poll();
}
