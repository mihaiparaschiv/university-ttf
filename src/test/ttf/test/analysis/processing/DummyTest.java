package ttf.test.analysis.processing;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ttf.analysis.AnalysisController;
import ttf.analysis.processing.ProcessorFactory;
import ttf.model.entry.Entry;
import ttf.model.entry.EntryFactory;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.TextValue;
import ttf.test.analysis.LinkedListProvider;

public class DummyTest {
	private AnalysisController analysisController;
	
	@Before
	public void beforeClass() {
		EntryFactory factory = new EntryFactory();
		Entry entry = factory.build();
		entry.getAddress().setValue(new AddressValue("http://..."));
		entry.getName().setValue(new TextValue("A news article"));
		entry.getContent().setValue(new TextValue("News content"));
		entry.getDiscoveredAt().setValue(new DateValue(new Date()));
		
		LinkedListProvider entryProvider = new LinkedListProvider();
		entryProvider.add(entry);
		ProcessorFactory processorFactory = new DummyProcessorFactory();
		analysisController = new AnalysisController(entryProvider, processorFactory);
	}
	
	@Test
	public void testDummyTask() {
		analysisController.execute();
	}
}
