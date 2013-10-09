package kalkulaator;

public class Token {
	static final public int IDENTIFIER = 0;
	static final public int OPERATOR = 1;
	static final public int NUMBER = 2;
	static final public int ERROR = 3;
	public static final int RESERVED = 4;
	
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
			case RESERVED:   	t = "RESERVED";  	break;
			default:        	t = String.format("<unknown (%d)>", type); break;
		}
		return String.format("Token(%s, %s)", t, value);
	}	
}
