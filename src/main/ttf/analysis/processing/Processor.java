package ttf.analysis.processing;

import java.util.LinkedList;
import java.util.Queue;

import ttf.analysis.processing.task.Task;

public class Processor {
	protected final ProcessorStore store;
	protected final Queue<Task> taskQueue;

	public Processor(ProcessorStore store) {
		this.store = store;
		this.taskQueue = new LinkedList<Task>();
	}
	
	public void execute() {
		while (taskQueue.peek() != null) {
			taskQueue.poll().execute();
		}
	}
	
	public ProcessorStore getStore() {
		return store;
	}
	
	public void addTask(Task task) {
		taskQueue.offer(task);
	}
}
