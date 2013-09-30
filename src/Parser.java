
public class Parser {
	private String expression;
	private int offset;
	
	ASTNode parse(String str) throws Exception {
		expression = str;
		offset = 0;
		ASTNode ret = expr();
		if (ret == null || offset < expression.length())
			throw new Exception("Parse error at offset " + offset);
		return ret;
	}
	
	ASTNode expr() {
		skipWhitespace();
		ASTNode ret = null;
		
		// expr := '-' prod
		if (isValid() && expression.charAt(offset) == '-') {
			int off = offset;
			offset++;
			if ((ret = prod()) != null)
				return new UnaryMinus(ret);
			offset = off;
		}
		
		if ((ret = prod()) == null)
			return null;
		
		skipWhitespace();
		
		// expr := prod '+' expr
		if (isValid() && expression.charAt(offset) == '+') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = expr()) != null)
				return new Add(ret, right);
			offset = off;
		}
		
		// expr := prod '-' expr
		if (isValid() && expression.charAt(offset) == '-') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = expr()) != null)
				return new Sub(ret, right);
			offset = off;
		}
		
		// expr := prod
		
		return ret;
	}
	
	ASTNode unary_function() {
		skipWhitespace();
		// unary_function := identifier expr
		int off = offset;
		
		String name = identifier();
		if (name == null)
			return null;
		
		ASTNode arg = prod();
		if (arg == null) {
			offset = off;
			return null;
		}
		
		return new UnaryFunction(name, arg);
	}
	
	ASTNode binary_function() {
		skipWhitespace();
		int off = offset;
		
		String name = identifier();
		if (name == null)
			return null;
		
		ASTNode arg0 = prod();
		if (arg0 == null) {
			offset = off;
			return null;
		}
		
		ASTNode arg1 = prod();
		if (arg1 == null) {
			offset = off;
			return null;
		}
		
		return new BinaryFunction(name, arg0, arg1);
	}
	
	ASTNode variable() {
		String name;
		if ((name = identifier()) == null)
			return null;
		return new Variable(name);
	}
	
	ASTNode prod() {
		skipWhitespace();
		ASTNode ret;
		
		// prod := binary_function
		if ((ret = binary_function()) != null)
			return ret;
		
		// prod := unary_function
		if ((ret = unary_function()) != null)
			return ret;
		
		// prod := num
		if ((ret = num()) == null)
			return null;
		
		skipWhitespace();
		
		// prod := num '*' prod
		if (isValid() && expression.charAt(offset) == '*') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = prod()) != null)
				return new Mul(ret, right);
			offset = off;
		}
		
		// prod := num '/' prod
		if (isValid() && expression.charAt(offset) == '/') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = prod()) != null)
				return new Div(ret, right);
			offset = off;
		}
		
		// prod := num '%' prod
		if (isValid() && expression.charAt(offset) == '%') {
			int off = offset;
			offset++;
			ASTNode right;
			if ((right = prod()) != null)
				return new Mod(ret, right);
			offset = off;
		}
		
		// prod := num
		
		return ret;
	}
	
	String identifier() {
		skipWhitespace();
		if (!isValid() || !Character.isJavaIdentifierStart(expression.charAt(offset)))
			return null;
		String str = new String();
		str = str.concat(expression.substring(offset, offset+1));
		offset++;
		while (isValid() && Character.isJavaIdentifierPart(expression.charAt(offset))) {
			str = str.concat(expression.substring(offset, offset+1));
			offset++;
		}
		return str;
	}
	
	ASTNode literal() {
		skipWhitespace();
		String str = new String();
		boolean dot = false;
		while (isValid() && (nextIsDigit() || (!dot && (expression.charAt(offset) == '.')))) {
			str = str.concat(expression.substring(offset, offset+1));
			if (expression.charAt(offset) == '.')
				dot = true;
			offset++;
		}
		
		if (str.isEmpty())
			return null;
		
		return new Number(str);
	}
	
	boolean isValid() {
		return offset < expression.length();
	}
	
	ASTNode num() {
		ASTNode ret = null;
		if ((ret = literal()) != null)
			return ret;
		if ((ret = variable()) != null)
			return ret;
		if (isValid() && expression.charAt(offset) == '(') {
			int off = offset;
			offset++;
			ret = expr();
			if (ret != null && isValid() && expression.charAt(offset) == ')') {
				offset++;
				return ret;
			}
			offset = off;
		}
		return null;
	}
	
	boolean nextIsDigit() {
		return (expression.charAt(offset) >= '0') && (expression.charAt(offset) <= '9');
	}
	
	void skipWhitespace() {
		while (isValid() && Character.isWhitespace(expression.charAt(offset)))
			offset++;
	}
}
