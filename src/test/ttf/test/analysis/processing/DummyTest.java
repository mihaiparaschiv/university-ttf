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

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

import ttf.analysis.AnalysisController;
import ttf.analysis.processor.Processor;
import ttf.model.entry.Entry;
import ttf.model.entry.EntryFactory;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.TextValue;
import ttf.test.analysis.LinkedListProvider;

public class DummyTest extends TestCase {
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
		Processor processor = new DummyProcessor();
		AnalysisController controller = new AnalysisController(entryProvider,
				processor);
		controller.run();
		assertEquals(NEW_NAME, entry.getName().getValue().get());
	}

	private class DummyCommand implements Command {
		@Override
		public boolean execute(Context context) {
//			Entry entry = ((DummyContext) context).getEntry();
//			System.out.println(entry);
//			entry.getName().setValue(new TextValue(NEW_NAME));
			return false;
		}
	}

	private class DummyContext extends ContextBase {
		private Entry entry;

		public void setEntry(Entry entry) {
			this.entry = entry;
		}

		public Entry getEntry() {
			return entry;
		}
	}

	private class DummyProcessor implements Processor {
		@Override
		public void process(Entry entry) {
			DummyContext context = new DummyContext();
//			context.setEntry(entry);
		}
	}
}
