package ttf.analysis;

import ttf.analysis.input.EntryProvider;
import ttf.model.entry.Entry;

public class AnalysisController {
	private final EntryProvider entryProvider;
	
	public AnalysisController(EntryProvider entryProvider) {
		this.entryProvider = entryProvider;
	}
	
	public void start() {
		Entry entry = null;
		while ((entry = entryProvider.poll()) != null) {
			EntryProcessor processor = new EntryProcessor(entry);
			processor.process();
		}
	}
}
