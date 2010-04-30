/*
 * Copyright 2010 Mihai Paraschiv
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
