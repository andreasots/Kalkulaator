package kalkulaator.parser;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public interface InfixOperator {
	public Node parse(Parser parser, Node left, Token token) throws Exception;
	public int precedence();
}
