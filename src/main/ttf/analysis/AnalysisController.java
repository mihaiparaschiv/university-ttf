package ttf.analysis;

import ttf.analysis.input.EntryProvider;
import ttf.analysis.input.IncomingEntry;

public class AnalysisController {
	private final EntryProvider entryProvider;
	
	public AnalysisController(EntryProvider entryProvider) {
		this.entryProvider = entryProvider;
	}
	
	public void start() {
		IncomingEntry incomingEntry = null;
		while ((incomingEntry = entryProvider.poll()) != null) {
			EntryProcessor processor = new EntryProcessor(incomingEntry);
			processor.process();
		}
	}
}
