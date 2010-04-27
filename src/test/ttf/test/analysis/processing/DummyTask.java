package ttf.test.analysis.processing;

import ttf.analysis.processing.Processor;
import ttf.analysis.processing.task.Task;

public class DummyTask extends Task {
	public DummyTask(Processor processor) {
		super(processor);
	}
	
	@Override
	public void execute() {
		System.out.println(processor.getStore().getEntry());
	}
}
