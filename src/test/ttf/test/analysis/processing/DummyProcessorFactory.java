package ttf.test.analysis.processing;

import ttf.analysis.processing.Processor;
import ttf.analysis.processing.ProcessorFactory;
import ttf.analysis.processing.ProcessorStore;
import ttf.model.entry.Entry;

public class DummyProcessorFactory implements ProcessorFactory {
	@Override
	public Processor build(Entry entry) {
		ProcessorStore store = new ProcessorStore();
		store.setEntry(entry);
		Processor processor = new Processor(store);
		processor.addTask(new DummyTask(processor));
		return processor;
	}
}
