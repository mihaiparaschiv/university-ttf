package ttf.model.entry;

import ttf.model.Model;
import ttf.model.property.Property;
import ttf.model.property.PropertyGroup;
import ttf.model.property.key.StringKey;
import ttf.model.property.value.AddressValue;
import ttf.model.property.value.DateValue;
import ttf.model.property.value.DoubleValue;
import ttf.model.property.value.SetValue;
import ttf.model.property.value.TextValue;
import ttf.model.topic.TopicValue;

/**
 * Provides access to information pertaining to a story.
 * 
 * @author Mihai Paraschiv
 */
public class Entry extends Model<EntryId> {
	private Property<AddressValue> address;
	private Property<TextValue> name;
	private Property<TextValue> author;
	private Property<DateValue> publishedAt;
	private Property<DateValue> discoveredAt;
	private Property<TextValue> content;
	private Property<DoubleValue> score;
	private Property<TopicValue> topic;
	private Property<SetValue<String>> tags;

	private PropertyGroup<StringKey, DoubleValue> termGroup;

	protected Entry(EntryId id) {
		super(id);
		address = new Property<AddressValue>(null);
		name = new Property<TextValue>(null);
		author = new Property<TextValue>(null);
		publishedAt = new Property<DateValue>(null);
		discoveredAt = new Property<DateValue>(null);
		content = new Property<TextValue>(null);
		score = new Property<DoubleValue>(null);
		tags = new Property<SetValue<String>>(null);
		topic = new Property<TopicValue>(null);
	}

	protected Entry() {
		this(null);
	}

	public Property<AddressValue> getAddress() {
		return address;
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

	public Property<DateValue> getDiscoveredAt() {
		return discoveredAt;
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

	@Override
	public String toString() {
		return "Entry [address=" + address + ", author=" + author
				+ ", content=" + content + ", discoveredAt=" + discoveredAt
				+ ", name=" + name + ", publishedAt=" + publishedAt
				+ ", score=" + score + ", tags=" + tags + ", termGroup="
				+ termGroup + ", topic=" + topic + "]";
	}
}
