package ttf.model.topic;

import ttf.model.Model;
import ttf.model.entry.EntryKey;
import ttf.model.store.property.PropertyGroup;
import ttf.model.store.property.value.DoubleValue;

/**
 * A topic contains entries and subtopics.
 * 
 * @author Mihai Paraschiv
 */
public class Topic extends Model<TopicId> {
	private PropertyGroup<EntryKey, DoubleValue> entries;
	private PropertyGroup<TopicKey, DoubleValue> topics;

	protected Topic(TopicId id) {
		super(id);
		this.entries = new PropertyGroup<EntryKey, DoubleValue>();
		this.topics = new PropertyGroup<TopicKey, DoubleValue>();
	}

	protected Topic() {
		this(null);
	}

	public PropertyGroup<EntryKey, DoubleValue> getEntries() {
		return entries;
	}

	public PropertyGroup<TopicKey, DoubleValue> getTopics() {
		return topics;
	}
}
