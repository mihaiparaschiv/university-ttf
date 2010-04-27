package ttf.analysis.processing.task;

import ttf.analysis.processing.Processor;

public abstract class Task {
	protected final Processor processor;
	
	public Task(Processor processor) {
		this.processor = processor;
	}
	
	public abstract void execute();
}
