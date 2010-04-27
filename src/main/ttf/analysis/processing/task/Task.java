package ttf.analysis.processing.task;

import ttf.analysis.processing.Processor;
import ttf.analysis.processing.store.ProcessingStore;

public abstract class Task<S extends ProcessingStore> {
	protected final Processor<S> processor;
	
	public Task(Processor<S> processor) {
		this.processor = processor;
	}
	
	public abstract void execute();
}
