package ttf.model;

public abstract class ModelId {
	protected final String id;

	public ModelId(String id) {
		this.id = id;
	}

	public String getValue() {
		return id;
	}
}
