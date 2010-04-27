package ttf.analysis.processing.store;

import ttf.model.entry.Entry;
import ttf.model.topic.Topic;

public class SimpleStore implements ProcessingStore {
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
