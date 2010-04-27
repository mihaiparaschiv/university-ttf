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
package ttf.test.analysis.processing;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ttf.analysis.AnalysisController;
import ttf.analysis.processing.Processor;
import ttf.analysis.processing.ProcessorFactory;
import ttf.analysis.processing.store.SimpleStore;
import ttf.analysis.processing.task.Task;
import ttf.model.entry.Entry;
import ttf.model.entry.EntryFactory;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.TextValue;
import ttf.test.analysis.LinkedListProvider;

public class DummyTest {
	private static final String NEW_NAME = "new name";

	@Test
	public void testDummyTask() {
		EntryFactory factory = new EntryFactory();
		Entry entry = factory.build();
		entry.getAddress().setValue(new AddressValue("http://..."));
		entry.getName().setValue(new TextValue("A news article"));
		entry.getContent().setValue(new TextValue("News content"));
		entry.getDiscoveredAt().setValue(new DateValue(new Date()));

		LinkedListProvider entryProvider = new LinkedListProvider();
		entryProvider.add(entry);
		ProcessorFactory<?> processorFactory = new DummyProcessorFactory();
		AnalysisController controller = new AnalysisController(entryProvider,
				processorFactory);
		controller.execute();
		assertEquals(NEW_NAME, entry.getName().getValue().get());
	}

	private class DummyTask extends Task<SimpleStore> {
		public DummyTask(Processor<SimpleStore> processor) {
			super(processor);
		}

		@Override
		public void execute() {
			Entry entry = processor.getStore().getEntry();
			System.out.println(entry);
			entry.getName().setValue(new TextValue(NEW_NAME));
		}
	}

	private class DummyProcessorFactory implements ProcessorFactory<SimpleStore> {
		@Override
		public Processor<SimpleStore> build(Entry entry) {
			SimpleStore store = new SimpleStore();
			store.setEntry(entry);
			Processor<SimpleStore> processor = new Processor<SimpleStore>(store);
			processor.addTask(new DummyTask(processor));
			return processor;
		}
	}
}
