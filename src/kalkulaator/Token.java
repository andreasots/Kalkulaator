package kalkulaator;

public class Token {
	static final public int IDENTIFIER = 0;
	static final public int OPERATOR = 1;
	static final public int NUMBER = 2;
	static final public int ERROR = 3;
	
	private int type;
	private Object value;
	
	Token(int t, Object v) {
		type = t;
		value = v;
	}
	
	public int type() {
		return type;
	}
	
	public Object value() {
		return value;
	}
	
	@Override
	public String toString() {
		String t;
		switch (type) {
			case IDENTIFIER:	t = "IDENTIFIER";	break;
			case OPERATOR:  	t = "OPERATOR";  	break;
			case NUMBER:    	t = "NUMBER";    	break;
			case ERROR:     	t = "ERROR";    	break;
			default:        	t = String.format("<unknown (%d)>", type); break;
		}
		return String.format("Token(%s, %s)", t, value);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Token))
			return false;
		Token other = (Token) o;
		return other.type == type && value != null && value.equals(other); 
	}
}
