
public class Parser {
	private String expression;
	private int offset;
	
	ASTNode parse(String str) throws Exception {
		expression = str;
		offset = 0;
		ASTNode ret = expr();
		if (ret == null || offset < expression.length())
			throw new Exception("Parse error: ");
		return ret;
	}
	
	ASTNode expr() {
		ASTNode ret = null;
		
		if (isValid() && expression.charAt(offset) == '-') {
			int off = offset;
			offset++;
			if ((ret = prod()) != null)
				return new UnaryMinus(ret);
			offset = off;
		}
		
		ret = prod();
		if (ret == null)
			return null;
		
		if (isValid() && expression.charAt(offset) == '+') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = expr()) != null)
				return new Add(ret, right);
			offset = off;
		}
		
		if (isValid() && expression.charAt(offset) == '-') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = expr()) != null)
				return new Sub(ret, right);
			offset = off;
		}
		
		return ret;
	}
	
	ASTNode prod() {
		ASTNode ret = num();
		if (ret == null)
			return null;
		
		if (isValid() && expression.charAt(offset) == '*') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = prod()) != null)
				return new Mul(ret, right);
			offset = off;
		}
		
		if (isValid() && expression.charAt(offset) == '/') {
			int off = offset;
			offset++;
			ASTNode right = null;
			if ((right = prod()) != null)
				return new Div(ret, right);
			offset = off;
		}
		
		return ret;
	}
	
	ASTNode literal() {
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
}
