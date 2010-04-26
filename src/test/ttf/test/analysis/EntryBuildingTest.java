package ttf.test.analysis;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ttf.analysis.incoming.IncomingEntry;
import ttf.model.entry.Entry;
import ttf.model.entry.EntryFactory;

public class EntryBuildingTest {
	private final String name;
	private final String author;
	private final Date publishedAt;
	private final Date discoveredAt;
	private final String content;
	private final Set<String> tags;

	public EntryBuildingTest() {
		name = "ArticleName";
		author = "FirstName LastName";
		publishedAt = new Date();
		discoveredAt = new Date();
		content = "Content of the article";
		tags = new HashSet<String>();
		tags.add("tag 1");
		tags.add("tag 2");
	}

	@Test
	public void testEntryBuildingFromIncoming() {
		IncomingEntry incomingEntry = new IncomingEntry();
		incomingEntry.setName(name);
		incomingEntry.setAuthor(author);
		incomingEntry.setPublishedAt(publishedAt);
		incomingEntry.setDiscoveredAt(discoveredAt);
		incomingEntry.setContent(content);
		incomingEntry.setTags(tags);

		Entry entry = new EntryFactory().buildFromIncoming(incomingEntry);
		
		assertEquals(name, entry.getName().getValue().get());
		assertEquals(author, entry.getAuthor().getValue().get());
		assertEquals(publishedAt, entry.getPublishedAt().getValue().get());
		assertEquals(discoveredAt, entry.getDiscoveredAt().getValue().get());
		assertEquals(content, entry.getContent().getValue().get());
		assertEquals(tags, entry.getTags().getValue().get());
	}
}
