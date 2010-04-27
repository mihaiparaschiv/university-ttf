package ttf.test.analysis.processing;

import static junit.framework.Assert.*;

import java.util.Date;

import org.junit.Test;

import ttf.analysis.AnalysisController;
import ttf.analysis.processing.Processor;
import ttf.analysis.processing.ProcessorFactory;
import ttf.analysis.processing.ProcessorStore;
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
		ProcessorFactory processorFactory = new DummyProcessorFactory();
		AnalysisController controller = new AnalysisController(entryProvider,
				processorFactory);
		controller.execute();
		assertEquals(NEW_NAME, entry.getName().getValue().get());
	}

	private class DummyTask extends Task {
		public DummyTask(Processor processor) {
			super(processor);
		}

		@Override
		public void execute() {
			Entry entry = processor.getStore().getEntry();
			System.out.println(entry);
			entry.getName().setValue(new TextValue(NEW_NAME));
		}
	}

	private class DummyProcessorFactory implements ProcessorFactory {
		@Override
		public Processor build(Entry entry) {
			ProcessorStore store = new ProcessorStore();
			store.setEntry(entry);
			Processor processor = new Processor(store);
			processor.addTask(new DummyTask(processor));
			return processor;
		}
	}
}
