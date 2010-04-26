package ttf.model.entry;

import ttf.model.Model;
import ttf.model.store.property.Property;
import ttf.model.store.property.PropertyGroup;
import ttf.model.store.property.key.StringKey;
import ttf.model.store.property.value.DateValue;
import ttf.model.store.property.value.DoubleValue;
import ttf.model.store.property.value.SetValue;
import ttf.model.store.property.value.TextValue;
import ttf.model.topic.TopicValue;

/**
 * Provides access to information pertaining to a story. This is a proof of
 * concept model.
 * 
 * @author Mihai Paraschiv
 */
public class Entry extends Model<EntryId> {
	private Property<TextValue> name;
	private Property<TextValue> author;
	private Property<DateValue> publishedAt;
	private Property<TextValue> content;
	private Property<DoubleValue> score;
	private Property<TopicValue> topic;
	private Property<SetValue<String>> tags;
	
	private PropertyGroup<StringKey, DoubleValue> termGroup;

	protected Entry(EntryId id) {
		super(id);
		name = new Property<TextValue>(null);
		author = new Property<TextValue>(null);
		publishedAt = new Property<DateValue>(null);
		content = new Property<TextValue>(null);
		score = new Property<DoubleValue>(null);
		tags = new Property<SetValue<String>>(null);
		topic = new Property<TopicValue>(null);
	}

	protected Entry() {
		this(null);
	}

	public Property<TextValue> getName() {
		return name;
	}

	public Property<TextValue> getAuthor() {
		return author;
	}

	public Property<DateValue> getPublishedAt() {
		return publishedAt;
	}

	public Property<TextValue> getContent() {
		return content;
	}

	public Property<DoubleValue> getScore() {
		return score;
	}

	public Property<TopicValue> getTopic() {
		return topic;
	}
	
	public Property<SetValue<String>> getTags() {
		return tags;
	}

	public PropertyGroup<StringKey, DoubleValue> getTermGroup() {
		return termGroup;
	}
}
