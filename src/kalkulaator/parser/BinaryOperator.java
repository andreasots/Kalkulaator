package kalkulaator.parser;

import java.lang.reflect.InvocationTargetException;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public class BinaryOperator implements InfixOperator {
	public Class<?> operator;
	public int priority;
	public boolean left_assoc;
	
	public BinaryOperator(Class<?> operator, int priority, boolean left_assoc) {
		this.operator = operator;
		this.priority = priority;
		this.left_assoc = left_assoc;
	}
		
	public Node parse(Parser parser, Node left, Token tok) throws Exception {
		return construct(left, parser.expr(left_assoc? priority: priority-1));
	}
	
	public Node construct(Node l, Node r) {
		try {
			return (Node) operator.getConstructor(Node.class, Node.class).newInstance(l, r);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int precedence() {
		return priority;
	}
}