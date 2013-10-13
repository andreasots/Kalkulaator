package kalkulaator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kalkulaator.ast.Identifier;
import kalkulaator.ast.Node;
import kalkulaator.ast.Add;
import kalkulaator.ast.Assign;
import kalkulaator.ast.Div;
import kalkulaator.ast.Mod;
import kalkulaator.ast.Mul;
import kalkulaator.ast.Number;
import kalkulaator.ast.Sub;
import kalkulaator.ast.UnaryMinus;
import kalkulaator.parser.BinaryOperator;
import kalkulaator.parser.Call;
import kalkulaator.parser.Conditional;
import kalkulaator.parser.Function;
import kalkulaator.parser.InfixOperator;
import kalkulaator.parser.Parens;
import kalkulaator.parser.PrefixOperator;
import kalkulaator.parser.UnaryOperator;


public class Parser {
	private String expression;
	private int offset;
	private Map<String, Function> names = new HashMap<String, Function>();
	private Map<String, InfixOperator> infix = new HashMap<String, InfixOperator>();
	private Map<String, PrefixOperator> prefix = new HashMap<String, PrefixOperator>();
	private Deque<Token> tokens = new ArrayDeque<Token>();
	
	Parser() {
		names.put("abs", new Function("abs") {
			public double eval(double x) {
				return Math.abs(x);
			}
		});
		names.put("acos", new Function("acos") {
			public double eval(double a) {
				return Math.acos(a);
			}
		});
		names.put("asin", new Function("asin") {
			public double eval(double a) {
				return Math.asin(a);
			}
		});
		names.put("atan", new Function("atan") {
			public double eval(double a) {
				return Math.atan(a);
			}
		});		
		names.put("atan2", new Function("atan2") {
			public double eval(double y, double x) {
				return Math.atan2(y, x);
			}
		});
		names.put("cbrt", new Function("cbrt") {
			public double eval(double a) {
				return Math.cbrt(a);
			}
		});		
		names.put("ceil", new Function("ceil") {
			public double eval(double a) {
				return Math.ceil(a);
			}
		});
		names.put("copySign", new Function("copySign") {
			public double eval(double magnitude, double sign) {
				return Math.copySign(magnitude, sign);
			}
		});
		names.put("cos", new Function("cos") {
			public double eval(double a) {
				return Math.cos(a);
			}
		});
		names.put("cosh", new Function("cosh") {
			public double eval(double a) {
				return Math.cosh(a);
			}
		});
		names.put("e", new Function("e") {
			public double eval() {
				return Math.E;
			}
		});
		names.put("exp", new Function("exp") {
			public double eval(double a) {
				return Math.exp(a);
			}
		});
		names.put("expm1", new Function("expm1") {
			public double eval(double a) {
				return Math.expm1(a);
			}
		});
		names.put("floor", new Function("floor") {
			public double eval(double a) {
				return Math.floor(a);
			}
		});
		names.put("getExponent", new Function("getExponent") {
			public double eval(double d) {
				return Math.getExponent(d);
			}
		});
		names.put("hypot", new Function("hypot") {
			public double eval(double x, double y) {
				return Math.hypot(x, y);
			}
		});		
		names.put("IEEEremainder", new Function("IEEEremainder") {
			public double eval(double f1, double f2) {
				return Math.IEEEremainder(f1, f2);
			}
		});
		names.put("log", new Function("log") {
			public double eval(double a) {
				return Math.log(a);
			}
		});
		names.put("log10", new Function("log10") {
			public double eval(double a) {
				return Math.log10(a);
			}
		});
		names.put("log1p", new Function("log1p") {
			public double eval(double a) {
				return Math.log1p(a);
			}
		});
		names.put("max", new Function("max") {
			public double eval(double a, double b) {
				return Math.max(a, b);
			}
		});
		names.put("min", new Function("min") {
			public double eval(double a, double b) {
				return Math.min(a, b);
			}
		});
		names.put("nextAfter", new Function("nextAfter") {
			public double eval(double start, double direction) {
				return Math.nextAfter(start, direction);
			}
		});
		names.put("nextUp", new Function("nextUp") {
			public double eval(double d) {
				return Math.nextUp(d);
			}
		});
		names.put("pi", new Function("pi") {
			public double eval() {
				return Math.PI;
			}
		});
		names.put("pow", new Function("pow") {
			public double eval(double a, double b) {
				return Math.pow(a, b);
			}
		});
		names.put("print", new Function("print") {
			public double eval(double x) {
				System.out.println(x);
				return x;
			}
		});
		names.put("random", new Function("random") {
			public double eval() {
				return Math.random();
			}
		});
		names.put("rint", new Function("rint") {
			public double eval(double a) {
				return Math.rint(a);
			}
		});
		names.put("round", new Function("round") {
			public double eval(double a) {
				return Math.round(a);
			}
		});
		names.put("scalb", new Function("scalb") {
			public double eval(double d, double scaleFactor) {
				return Math.scalb(d, (int) scaleFactor);
			}
		});
		names.put("signum", new Function("signum") {
			public double eval(double a) {
				return Math.signum(a);
			}
		});
		names.put("sin", new Function("sin") {
			public double eval(double a) {
				return Math.sin(a);
			}
		});
		names.put("sinh", new Function("sinh") {
			public double eval(double a) {
				return Math.sinh(a);
			}
		});
		names.put("sqrt", new Function("sqrt") {
			public double eval(double a) {
				return Math.sqrt(a);
			}
		});
		names.put("tan", new Function("tan") {
			public double eval(double a) {
				return Math.tan(a);
			}
		});
		names.put("tanh", new Function("tanh") {
			public double eval(double a) {
				return Math.tanh(a);
			}
		});
		names.put("toDegrees", new Function("toDegrees") {
			public double eval(double a) {
				return Math.toDegrees(a);
			}
		});
		names.put("toRadians", new Function("toRadians") {
			public double eval(double a) {
				return Math.toRadians(a);
			}
		});
		names.put("ulp", new Function("ulp") {
			public double eval(double a) {
				return Math.ulp(a);
			}
		});
		
		register("(", new Parens());
		register("-", new UnaryOperator(UnaryMinus.class, 4));
		
		register("=", new BinaryOperator(Assign.class, 1, true));
		register("?", new Conditional(2));
		register("+", new BinaryOperator(Add.class, 3, true));
		register("-", new BinaryOperator(Sub.class, 3, true));
		register("*", new BinaryOperator(Mul.class, 4, true));
		register("/", new BinaryOperator(Div.class, 4, true));
		register("%", new BinaryOperator(Mod.class, 4, true));
		register("(", new Call(5));
	}
	
	private void register(String token, PrefixOperator op) {
		prefix.put(token, op);
	}
	
	private void register(String token, InfixOperator op) {
		infix.put(token, op);
	}

	Node parse(String str) throws Exception {
		expression = str;
		offset = 0;
		tokens.clear();
		
		Node ret = expr(0);
		if (ret == null || offset < expression.length())
			throw new Exception(String.format("Parssimise viga peale %d. sümbolit", offset));
		return ret;
	}
	
	int nextPrecedence() {
		if (look().type() == Token.OPERATOR) {
			InfixOperator op = infix.get(look().value());
			if (op != null)
				return op.precedence();
		}
		return 0;
	}
	
	public Node expr(int precedence) throws Exception {
		Token token = nextToken();
		
		if (token.type() == Token.ERROR)
			throw new Exception("Ootamatu lõpp");
		
		Node left = null;
		if (token.type() == Token.NUMBER) {
			left = new Number((Double)token.value());
		} else if (token.type() == Token.IDENTIFIER) {
			left = new Identifier(names, (String)token.value());
		} else {
			PrefixOperator op = prefix.get(token.value());
			if (op == null)
				throw new Exception(String.format("Tehe '%s' defineerimata", token.value()));
			left = op.parse(this, token);
		}
		
		while (precedence < nextPrecedence()) {
			token = nextToken();
			if (token.type() != Token.OPERATOR)
				throw new Exception("Oodati tehtemärki");
			InfixOperator op = infix.get(token.value());
			if (op == null)
				throw new Exception(String.format("Tehe '%s' defineerimata", token.value()));
			left = op.parse(this, left, token);
		}
		
		return left;
	}
	
	private void pushToken(Token token) {
		tokens.push(token);
	}

	public Function resolve(Object object) throws Exception {
		Function ret = names.get(object);
		if (ret == null)
			throw new Exception(String.format("Sümbol '%s' defineerimata", object));
		return ret;
	}
	
	public Token look() {
		if (tokens.isEmpty())
			readToken();
		return tokens.peekFirst();
	}
	
	public Token nextToken() {
		if (tokens.isEmpty())
			readToken();
		return tokens.pop();
	}
	
	void readToken() {
		skipWhitespace();
		String str;
		Double d;
		if ((str = identifier()) != null)
			pushToken(new Token(Token.IDENTIFIER, str));
		else if ((str = operator()) != null)
			pushToken(new Token(Token.OPERATOR, str));
		else if ((d = number()) != null)
			pushToken(new Token(Token.NUMBER, d));
		else
			pushToken(new Token(Token.ERROR, null));
	}
	
	String operator() {
		if (!isValid())
			return null;
		char c = expression.charAt(offset);
		if (!Character.isLetter(c) && !Character.isDigit(c) && !Character.isWhitespace(c)) {
			offset++;
			return Character.toString(c);
		}
		return null;
	}
	
	String identifier() {
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
	
	Double number() {
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
		
		return Double.parseDouble(str);
	}
	
	boolean isValid() {
		return offset < expression.length();
	}
	
	boolean nextIsDigit() {
		return (expression.charAt(offset) >= '0') && (expression.charAt(offset) <= '9');
	}
	
	void skipWhitespace() {
		while (isValid() && Character.isWhitespace(expression.charAt(offset)))
			offset++;
	}

	public List<String> names() {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(names.keySet());
		Collections.sort(list);
		return list;
	}
}
