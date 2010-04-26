package ttf.persistence;

import ttf.model.entry.Entry;
import ttf.model.topic.Topic;

public interface ModelProvider {

	public Entry getEntryById(int id);

	public Topic getTopicById(int id);

	public Entry[] getEntries(Query query);

	public Topic[] getTopics(TopicQuery query);

}
