package ttf.model.token;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Models a word token. Also contains information about the type of entity the
 * token represents.
 */

public class Token{

	protected Token() {
		super();
	}

	protected Token(String value, TokenType type) {
		this();
		setValue(value);
		setType(type);
		setCount(1);
	}

	private String value;
	private TokenType type;
	private int count;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return value + " (" + type + ") - Count " + count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void incrementCount(){
		count++;
	}

}
