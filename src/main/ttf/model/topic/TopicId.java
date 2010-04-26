package ttf.model.topic;

import ttf.model.ModelId;
import ttf.model.entry.EntryKey;
import ttf.model.store.property.PropertyGroup;
import ttf.model.store.property.value.DoubleValue;

public class TopicId extends ModelId {
	private PropertyGroup<EntryKey, DoubleValue> entries;
	private PropertyGroup<TopicKey, DoubleValue> topic;
	
	public TopicId(String id) {
		super(id);
	}
	
	public PropertyGroup<EntryKey, DoubleValue> getEntries() {
		return entries;
	}
	
	public PropertyGroup<TopicKey, DoubleValue> getTopic() {
		return topic;
	}
}
