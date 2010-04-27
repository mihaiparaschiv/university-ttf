package ttf.analysis.processing;

import java.util.LinkedList;
import java.util.Queue;

import ttf.analysis.processing.store.ProcessingStore;
import ttf.analysis.processing.task.Task;

public class Processor<S extends ProcessingStore> {
	protected final S store;
	protected final Queue<Task<S>> taskQueue;

	public Processor(S store) {
		this.store = store;
		this.taskQueue = new LinkedList<Task<S>>();
	}
	
	public void execute() {
		while (taskQueue.peek() != null) {
			taskQueue.poll().execute();
		}
	}
	
	public S getStore() {
		return store;
	}
	
	public void addTask(Task<S> task) {
		taskQueue.offer(task);
	}
}
