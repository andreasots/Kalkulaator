package kalkulaator.parser;

import java.util.ArrayList;
import java.util.List;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.BinaryFunction;
import kalkulaator.ast.Identifier;
import kalkulaator.ast.Node;
import kalkulaator.ast.UnaryFunction;
import kalkulaator.ast.Variable;

public class Call implements InfixOperator {
	private int precedence;
	public Call(int precedence) {
		this.precedence = precedence;
	}

	@Override
	public Node parse(Parser parser, Node left, Token token) throws Exception {
		List<Node> args = new ArrayList<Node>();
		
		if (!(left instanceof Identifier))
			throw new Exception(String.format("Oodati funktsiooni nime, leiti '%s'", left));
		
		token = parser.look();
		if (token.type() != Token.OPERATOR || !token.value().equals(")")) {
			do {
				args.add(parser.expr(0));
				token = parser.nextToken();
			} while (token.type() == Token.OPERATOR && token.value().equals(","));
			if (token.type() != Token.OPERATOR || !token.value().equals(")"))
				throw new Exception(String.format("Oodati paremat sulgu, leiti '%s'", token.value()));
		}
		
		switch (args.size()) {
		case 0:
			return new Variable(parser.resolve(((Identifier)left).name));
		case 1:
			return new UnaryFunction(parser.resolve(((Identifier)left).name), args.get(0));
		case 2:
			return new BinaryFunction(parser.resolve(((Identifier)left).name), args.get(0), args.get(1));
		default:
			throw new Exception(String.format("Funktsioonil '%s' on liiga palju argumente", ((Identifier)left).name));
		} 
	}

	@Override
	public int precedence() {
		return precedence;
	}
}