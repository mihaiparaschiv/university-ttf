package ttf.model.topic;

import ttf.model.Model;

/**
 * Proof of concept model for accessing topic information.
 * 
 * @author Mihai Paraschiv
 */
public class Topic extends Model<TopicId> {
	public Topic(TopicId id) {
		super(id);
	}
	
	public Topic() {
		this(null);
	}
}
