import java.util.HashMap;
import java.util.Map;


public class Parser {
	private String expression;
	private int offset;
	private Map<String, Function> names = new HashMap<String, Function>();
	
	Parser() {
		names.put("abs", new Function("abs") {
			double eval(double x) {
				return Math.abs(x);
			}
		});
		names.put("acos", new Function("acos") {
			double eval(double a) {
				return Math.acos(a);
			}
		});
		names.put("asin", new Function("asin") {
			double eval(double a) {
				return Math.asin(a);
			}
		});
		names.put("atan", new Function("atan") {
			double eval(double a) {
				return Math.atan(a);
			}
		});		
		names.put("atan2", new Function("atan2") {
			double eval(double y, double x) {
				return Math.atan2(y, x);
			}
		});
		names.put("cbrt", new Function("cbrt") {
			double eval(double a) {
				return Math.cbrt(a);
			}
		});		
		names.put("ceil", new Function("ceil") {
			double eval(double a) {
				return Math.ceil(a);
			}
		});
		names.put("copySign", new Function("copySign") {
			double eval(double magnitude, double sign) {
				return Math.copySign(magnitude, sign);
			}
		});
		names.put("cos", new Function("cos") {
			double eval(double a) {
				return Math.cos(a);
			}
		});
		names.put("cosh", new Function("cosh") {
			double eval(double a) {
				return Math.cosh(a);
			}
		});
		names.put("e", new Function("e") {
			double eval() {
				return Math.E;
			}
		});
		names.put("exp", new Function("exp") {
			double eval(double a) {
				return Math.exp(a);
			}
		});
		names.put("expm1", new Function("expm1") {
			double eval(double a) {
				return Math.expm1(a);
			}
		});
		names.put("floor", new Function("floor") {
			double eval(double a) {
				return Math.floor(a);
			}
		});
		names.put("getExponent", new Function("getExponent") {
			double eval(double d) {
				return Math.getExponent(d);
			}
		});
		names.put("hypot", new Function("hypot") {
			double eval(double x, double y) {
				return Math.hypot(x, y);
			}
		});		
		names.put("IEEEremainder", new Function("IEEEremainder") {
			double eval(double f1, double f2) {
				return Math.IEEEremainder(f1, f2);
			}
		});
		names.put("log", new Function("log") {
			double eval(double a) {
				return Math.log(a);
			}
		});
		names.put("log10", new Function("log10") {
			double eval(double a) {
				return Math.log10(a);
			}
		});
		names.put("log1p", new Function("log1p") {
			double eval(double a) {
				return Math.log1p(a);
			}
		});
		names.put("max", new Function("max") {
			double eval(double a, double b) {
				return Math.max(a, b);
			}
		});
		names.put("min", new Function("min") {
			double eval(double a, double b) {
				return Math.min(a, b);
			}
		});
		names.put("nextAfter", new Function("nextAfter") {
			double eval(double start, double direction) {
				return Math.nextAfter(start, direction);
			}
		});
		names.put("nextUp", new Function("nextUp") {
			double eval(double d) {
				return Math.nextUp(d);
			}
		});
		names.put("pi", new Function("pi") {
			double eval() {
				return Math.PI;
			}
		});
		names.put("pow", new Function("pow") {
			double eval(double a, double b) {
				return Math.pow(a, b);
			}
		});
		names.put("rint", new Function("rint") {
			double eval(double a) {
				return Math.rint(a);
			}
		});
		names.put("round", new Function("round") {
			double eval(double a) {
				return Math.round(a);
			}
		});
		names.put("scalb", new Function("scalb") {
			double eval(double d, double scaleFactor) {
				return Math.scalb(d, (int) scaleFactor);
			}
		});
		names.put("signum", new Function("signum") {
			double eval(double a) {
				return Math.signum(a);
			}
		});
		names.put("sin", new Function("sin") {
			double eval(double a) {
				return Math.sin(a);
			}
		});
		names.put("sinh", new Function("sinh") {
			double eval(double a) {
				return Math.sinh(a);
			}
		});
		names.put("sqrt", new Function("sqrt") {
			double eval(double a) {
				return Math.sqrt(a);
			}
		});
		names.put("tan", new Function("tan") {
			double eval(double a) {
				return Math.tan(a);
			}
		});
		names.put("tanh", new Function("tanh") {
			double eval(double a) {
				return Math.tanh(a);
			}
		});

		names.put("toDegrees", new Function("toDegrees") {
			double eval(double a) {
				return Math.toDegrees(a);
			}
		});
		names.put("toRadians", new Function("toRadians") {
			double eval(double a) {
				return Math.toRadians(a);
			}
		});
		names.put("ulp", new Function("ulp") {
			double eval(double a) {
				return Math.ulp(a);
			}
		});
/*	

	case "rint":
	case "round":*/
	}
	
	ASTNode parse(String str) throws Exception {
		expression = str;
		offset = 0;
		ASTNode ret = expr();
		if (ret == null || offset < expression.length())
			throw new Exception("Parse error at offset " + offset);
		return ret;
	}
	
	Function resolve(String name) {
		Function ret = names.get(name);
		if (ret == null)
			throw new RuntimeException(String.format("'%s' is not defined", name));
		return ret;
	}
	
	ASTNode expr() {
		skipWhitespace();
		ASTNode ret = null;
		
		// expr := identifier '=' expr
		{
			int off = offset;
			String name = identifier();
			skipWhitespace();
			if (name != null && isValid() && expression.charAt(offset) == '=') {
				offset++;
				skipWhitespace();
				if ((ret = expr()) != null)
					return new Assign(names, name, ret);
			}
			offset = off;
		}
		
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
		
		return new UnaryFunction(resolve(name), arg);
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
		
		return new BinaryFunction(resolve(name), arg0, arg1);
	}
	
	ASTNode variable() {
		String name;
		if ((name = identifier()) == null)
			return null;
		return new Variable(resolve(name));
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
