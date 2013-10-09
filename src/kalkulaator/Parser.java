package kalkulaator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kalkulaator.ast.Conditional;
import kalkulaator.ast.Node;
import kalkulaator.ast.Add;
import kalkulaator.ast.Assign;
import kalkulaator.ast.BinaryFunction;
import kalkulaator.ast.Div;
import kalkulaator.ast.Mod;
import kalkulaator.ast.Mul;
import kalkulaator.ast.Number;
import kalkulaator.ast.Sub;
import kalkulaator.ast.UnaryFunction;
import kalkulaator.ast.UnaryMinus;
import kalkulaator.ast.Variable;
import kalkulaator.parser.BinaryOperator;
import kalkulaator.parser.Function;


public class Parser {
	private String expression;
	private int offset;
	private Map<String, Function> names = new HashMap<String, Function>();
	private Map<String, BinaryOperator> operators = new HashMap<String, BinaryOperator>();
	private Token lookahead = null;
	
	Parser() {
		names.put("abs", new Function("abs", 1) {
			public double eval(double x) {
				return Math.abs(x);
			}
		});
		names.put("acos", new Function("acos", 1) {
			public double eval(double a) {
				return Math.acos(a);
			}
		});
		names.put("asin", new Function("asin", 1) {
			public double eval(double a) {
				return Math.asin(a);
			}
		});
		names.put("atan", new Function("atan", 1) {
			public double eval(double a) {
				return Math.atan(a);
			}
		});		
		names.put("atan2", new Function("atan2", 2) {
			public double eval(double y, double x) {
				return Math.atan2(y, x);
			}
		});
		names.put("cbrt", new Function("cbrt", 1) {
			public double eval(double a) {
				return Math.cbrt(a);
			}
		});		
		names.put("ceil", new Function("ceil", 1) {
			public double eval(double a) {
				return Math.ceil(a);
			}
		});
		names.put("copySign", new Function("copySign", 2) {
			public double eval(double magnitude, double sign) {
				return Math.copySign(magnitude, sign);
			}
		});
		names.put("cos", new Function("cos", 1) {
			public double eval(double a) {
				return Math.cos(a);
			}
		});
		names.put("cosh", new Function("cosh", 1) {
			public double eval(double a) {
				return Math.cosh(a);
			}
		});
		names.put("e", new Function("e", 0) {
			public double eval() {
				return Math.E;
			}
		});
		names.put("exp", new Function("exp", 1) {
			public double eval(double a) {
				return Math.exp(a);
			}
		});
		names.put("expm1", new Function("expm1", 1) {
			public double eval(double a) {
				return Math.expm1(a);
			}
		});
		names.put("floor", new Function("floor", 1) {
			public double eval(double a) {
				return Math.floor(a);
			}
		});
		names.put("getExponent", new Function("getExponent", 1) {
			public double eval(double d) {
				return Math.getExponent(d);
			}
		});
		names.put("hypot", new Function("hypot", 2) {
			public double eval(double x, double y) {
				return Math.hypot(x, y);
			}
		});		
		names.put("IEEEremainder", new Function("IEEEremainder", 2) {
			public double eval(double f1, double f2) {
				return Math.IEEEremainder(f1, f2);
			}
		});
		names.put("log", new Function("log", 1) {
			public double eval(double a) {
				return Math.log(a);
			}
		});
		names.put("log10", new Function("log10", 1) {
			public double eval(double a) {
				return Math.log10(a);
			}
		});
		names.put("log1p", new Function("log1p", 1) {
			public double eval(double a) {
				return Math.log1p(a);
			}
		});
		names.put("max", new Function("max", 2) {
			public double eval(double a, double b) {
				return Math.max(a, b);
			}
		});
		names.put("min", new Function("min", 2) {
			public double eval(double a, double b) {
				return Math.min(a, b);
			}
		});
		names.put("nextAfter", new Function("nextAfter", 2) {
			public double eval(double start, double direction) {
				return Math.nextAfter(start, direction);
			}
		});
		names.put("nextUp", new Function("nextUp", 1) {
			public double eval(double d) {
				return Math.nextUp(d);
			}
		});
		names.put("pi", new Function("pi", 0) {
			public double eval() {
				return Math.PI;
			}
		});
		names.put("pow", new Function("pow", 2) {
			public double eval(double a, double b) {
				return Math.pow(a, b);
			}
		});
		names.put("print", new Function("print", 1) {
			public double eval(double x) {
				System.out.println(x);
				return x;
			}
		});
		names.put("random", new Function("random", 0) {
			public double eval() {
				return Math.random();
			}
		});
		names.put("rint", new Function("rint", 1) {
			public double eval(double a) {
				return Math.rint(a);
			}
		});
		names.put("round", new Function("round", 1) {
			public double eval(double a) {
				return Math.round(a);
			}
		});
		names.put("scalb", new Function("scalb", 2) {
			public double eval(double d, double scaleFactor) {
				return Math.scalb(d, (int) scaleFactor);
			}
		});
		names.put("signum", new Function("signum", 1) {
			public double eval(double a) {
				return Math.signum(a);
			}
		});
		names.put("sin", new Function("sin", 1) {
			public double eval(double a) {
				return Math.sin(a);
			}
		});
		names.put("sinh", new Function("sinh", 1) {
			public double eval(double a) {
				return Math.sinh(a);
			}
		});
		names.put("sqrt", new Function("sqrt", 1) {
			public double eval(double a) {
				return Math.sqrt(a);
			}
		});
		names.put("tan", new Function("tan", 1) {
			public double eval(double a) {
				return Math.tan(a);
			}
		});
		names.put("tanh", new Function("tanh", 1) {
			public double eval(double a) {
				return Math.tanh(a);
			}
		});
		names.put("toDegrees", new Function("toDegrees", 1) {
			public double eval(double a) {
				return Math.toDegrees(a);
			}
		});
		names.put("toRadians", new Function("toRadians", 1) {
			public double eval(double a) {
				return Math.toRadians(a);
			}
		});
		names.put("ulp", new Function("ulp", 1) {
			public double eval(double a) {
				return Math.ulp(a);
			}
		});
		
		operators.put("+", new BinaryOperator(Add.class, 2, true));
		operators.put("-", new BinaryOperator(Sub.class, 2, true));
		operators.put("*", new BinaryOperator(Mul.class, 3, true));
		operators.put("/", new BinaryOperator(Div.class, 3, true));
		operators.put("%", new BinaryOperator(Mod.class, 3, true));
	}
	
	Node parse(String str) throws Exception {
		expression = str;
		offset = 0;
		lookahead = null;
		
		Node ret = expr();
		if (ret == null || offset < expression.length())
			throw new Exception(String.format("Parssimise viga peale %d. sümbolit", offset));
		return ret;
	}
	
	private void pushOperator(Deque<Node> output, Deque<Token> operators) throws Exception {
		BinaryOperator op = this.operators.get(operators.pop().value());
		Node r, l;
		try {
			r = output.pop();
		} catch (java.util.NoSuchElementException e) {
			throw new Exception("Operaatori paremat argumenti ei leitud");
		}
		try {
			l = output.pop();
		} catch (java.util.NoSuchElementException e) {
			throw new Exception("Operaatori vasakut argumenti ei leitud");
		}
		output.push(op.construct(l, r));
	}
	
	private void pushFunction(Deque<Node> output, Deque<Token> operators) throws Exception {
		Function f = resolve((String) operators.pop().value());
		switch (f.args) {
		case 0:
			output.push(new Variable(f));
			break;
		case 1:
			output.push(new UnaryFunction(f, output.pop()));
			break;
		case 2: {
			Node l = output.pop();
			Node r = output.pop();
			output.push(new BinaryFunction(f, l, r));
			break;
		}
		default:
			throw new Exception(String.format("Funktsioonil '%f' on liiga palju argumente", f.name));
		}
	}
	
	Node expr() throws Exception {
		Deque<Node> output = new ArrayDeque<Node>();
		Deque<Token> operators = new ArrayDeque<Token>();
		
		while (look().type() != Token.ERROR) {
			Token t = nextToken();
			switch(t.type()) {
			case Token.NUMBER:
				output.push(new Number((Double)t.value()));
				break;
			case Token.IDENTIFIER:
				operators.push(t);
				break;
			case Token.OPERATOR:
				if (t.value().equals(",")) {
					while (!operators.peek().value().equals("(")) {
						if (operators.peek().type() == Token.OPERATOR)
							pushOperator(output, operators);
						else
							pushFunction(output, operators);
					}
				} else if (this.operators.containsKey(t.value())) {
					Token tok;
					BinaryOperator o1 = this.operators.get(t.value());
					while (!operators.isEmpty() && (tok = operators.peek()).type() == Token.OPERATOR && this.operators.containsKey(tok.value())) {
						BinaryOperator o2 = this.operators.get(tok.value());
						if ((o1.left_assoc && o1.priority == o2.priority) || o1.priority < o2.priority)
							pushOperator(output, operators);
						else
							break;
					}
					operators.push(t);
				} else if (t.value().equals("(")) {
					operators.push(t);
				} else if (t.value().equals(")")) {
					while (!operators.peek().value().equals("("))
						if (operators.peek().type() == Token.OPERATOR)
							pushOperator(output, operators);
						else
							pushFunction(output, operators);
					operators.pop();
					if (!operators.isEmpty() && operators.peek().type() == Token.IDENTIFIER)
						pushFunction(output, operators);
				} else {
					throw new Exception(String.format("Defineerimata operaator '%s'", t.value()));
				}
				break;
			}
		}
		
		while (!operators.isEmpty())
			if (operators.peek().value().equals("(") || operators.peek().value().equals(")"))
				throw new Exception("Paariliseta sulg");
			else if (operators.peek().type() == Token.IDENTIFIER)
				pushFunction(output, operators);
			else
				pushOperator(output, operators);
		
		if (output.size() > 1)
			throw new Exception("Mitu süntaksipuud");
		else if (output.size() == 0)
			throw new Exception("Pole süntaksipuud");
		
		return output.pop();
	}
	
	Function resolve(String name) throws Exception {
		Function ret = names.get(name);
		if (ret == null)
			throw new Exception(String.format("Sümbol '%s' defineerimata", name));
		return ret;
	}
	
	Token look() {
		if (lookahead == null)
			readToken();
		return lookahead;
	}
	
	Token nextToken() {
		Token ret = look();
		lookahead = null;
		return ret;
	}
	
	void readToken() {
		skipWhitespace();
		String str;
		Double d;
		if ((str = identifier()) != null)
			lookahead = new Token(Token.IDENTIFIER, str);
		else if ((str = operator()) != null)
			lookahead = new Token(Token.OPERATOR, str);
		else if ((d = number()) != null)
			lookahead =  new Token(Token.NUMBER, d);
		else
			lookahead = new Token(Token.ERROR, null);
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
