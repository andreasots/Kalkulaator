package kalkulaator.parser;

import java.lang.reflect.InvocationTargetException;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public class UnaryOperator implements PrefixOperator {
	public Class<?> operator;
	public int priority;
	
	public UnaryOperator(Class<?> operator, int priority) {
		this.operator = operator;
		this.priority = priority;
	}
	
	public Node construct(Node node) {
		try {
			return (Node) operator.getConstructor(Node.class).newInstance(node);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Node parse(Parser parser, Token token) throws Exception {
		return construct(parser.expr(priority));
	}
}