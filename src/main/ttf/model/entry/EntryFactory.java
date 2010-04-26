package ttf.model.entry;

import ttf.analysis.incoming.IncomingEntry;
import ttf.model.store.property.value.DateValue;
import ttf.model.store.property.value.SetValue;
import ttf.model.store.property.value.TextValue;

public class EntryFactory {
	public Entry buildFromIncoming(IncomingEntry incomingEntry) {
		Entry entry = new Entry();
		
		TextValue name = new TextValue(incomingEntry.getName());
		entry.getName().setValue(name);
		
		TextValue author = new TextValue(incomingEntry.getAuthor());
		entry.getAuthor().setValue(author);
		
		DateValue publishedAt = new DateValue(incomingEntry.getPublishedAt());
		entry.getPublishedAt().setValue(publishedAt);
		
		TextValue content = new TextValue(incomingEntry.getContent());
		entry.getContent().setValue(content);
		
		SetValue<String> tags = new SetValue<String>(incomingEntry.getTags());
		entry.getTags().setValue(tags);
		
		return entry;
	}
}
