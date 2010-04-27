package ttf.analysis.input;

/**
 * Provides new entries.
 * 
 * @author Mihai Paraschiv
 * 
 */
public interface EntryProvider {
	public IncomingEntry poll();
}
