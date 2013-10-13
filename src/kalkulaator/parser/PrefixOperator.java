package kalkulaator.parser;

import kalkulaator.Parser;
import kalkulaator.Token;
import kalkulaator.ast.Node;

public interface PrefixOperator {
	public Node parse(Parser parser, Token token) throws Exception;
}
