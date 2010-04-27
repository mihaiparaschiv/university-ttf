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
package ttf.analysis;

import ttf.analysis.input.EntryProvider;
import ttf.analysis.processing.Processor;
import ttf.analysis.processing.ProcessorFactory;
import ttf.model.entry.Entry;

public class AnalysisController {
	private final EntryProvider entryProvider;
	private final ProcessorFactory<?> processorFactory;
	
	public AnalysisController(EntryProvider entryProvider, ProcessorFactory<?> processorFactory) {
		this.entryProvider = entryProvider;
		this.processorFactory = processorFactory;
	}
	
	public void execute() {
		Entry entry = null;
		while ((entry = entryProvider.poll()) != null) {
			Processor<?> processor = processorFactory.build(entry);
			processor.execute();
		}
	}
}
