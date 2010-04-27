package ttf.analysis.processing;

import ttf.analysis.processing.store.ProcessingStore;
import ttf.model.entry.Entry;

public interface ProcessorFactory<S extends ProcessingStore> {
	public Processor<S> build(Entry entry);
}
