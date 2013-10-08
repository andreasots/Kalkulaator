package kalkulaator.parser;

import java.lang.reflect.InvocationTargetException;

import kalkulaator.ast.Node;

public class BinaryOperator {
	public Class<?> operator;
	public int priority;
	public boolean left_assoc;
	
	public BinaryOperator(Class<?> operator, int priority, boolean left_assoc) {
		this.operator = operator;
		this.priority = priority;
		this.left_assoc = left_assoc;
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
}