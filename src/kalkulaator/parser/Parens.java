package kalkulaator.parser;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public class Parens implements PrefixOperator {

	@Override
	public Node parse(Parser parser, Token token) throws Exception {
		Node ret = parser.expr(0);
		token = parser.nextToken();
		if (token.type() != Token.OPERATOR || !token.value().equals(")"))
			throw new Exception(String.format("Oodati vasakut sulgu, aga leiti '%s'", token.value()));
		return ret;
	}

}
