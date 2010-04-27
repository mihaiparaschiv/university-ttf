package ttf.analysis.processing;

import ttf.model.entry.Entry;
import ttf.model.topic.Topic;

public class ProcessorStore {
	private Entry entry;
	private Topic topic;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
