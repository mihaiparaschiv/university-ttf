package ttf.analysis.processing;

import ttf.analysis.AnalysisController;
import ttf.analysis.processing.store.ProcessingStore;
import ttf.model.entry.Entry;

/**
 * The {@link AnalysisController} requires a processor factory.
 * 
 * @author Mihai Paraschiv
 *
 * @param <S> type of processing store
 */
public interface ProcessorFactory<S extends ProcessingStore> {
	public Processor<S> build(Entry entry);
}
