package ttf.analysis.input;

import java.util.Date;
import java.util.Set;

/**
 * Keeps all the information necessary for processing new entries.
 * 
 * @author Mihai Paraschiv
 *
 */
public class IncomingEntry {
	protected String name;
	protected String author;
	protected Date publishedAt;
	protected Date discoveredAt;
	protected String content;
	protected Set<String> tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Date getDiscoveredAt() {
		return discoveredAt;
	}

	public void setDiscoveredAt(Date discoveredAt) {
		this.discoveredAt = discoveredAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
