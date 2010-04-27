package ttf.analysis.processing;

import ttf.model.entry.Entry;

public interface ProcessorFactory {
	public Processor build(Entry entry);
}
