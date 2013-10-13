package kalkulaator.parser;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public class Conditional implements InfixOperator {
	private int precedence;
	public Conditional(int precedence) {
		this.precedence = precedence;
	}

	@Override
	public Node parse(Parser parser, Node left, Token token) throws Exception {
		Node branch1 = parser.expr(precedence);
		token = parser.nextToken();
		if (token.type() != Token.OPERATOR || !token.value().equals(":"))
			throw new Exception(String.format("Oodati ':', leiti '%s'", token.value()));
		Node branch2 = parser.expr(precedence);
		return new kalkulaator.ast.Conditional(left, branch1, branch2);
	}

	@Override
	public int precedence() {
		return precedence;
	}

}
