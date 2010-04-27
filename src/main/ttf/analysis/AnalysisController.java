package ttf.analysis;

import ttf.analysis.input.EntryProvider;
import ttf.analysis.processing.Processor;
import ttf.analysis.processing.ProcessorFactory;
import ttf.model.entry.Entry;

public class AnalysisController {
	private final EntryProvider entryProvider;
	private final ProcessorFactory processorFactory;
	
	public AnalysisController(EntryProvider entryProvider, ProcessorFactory processorFactory) {
		this.entryProvider = entryProvider;
		this.processorFactory = processorFactory;
	}
	
	public void execute() {
		Entry entry = null;
		while ((entry = entryProvider.poll()) != null) {
			Processor processor = processorFactory.build(entry);
			processor.execute();
		}
	}
}
