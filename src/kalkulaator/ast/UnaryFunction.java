package kalkulaator.ast;

import kalkulaator.parser.Function;

public class UnaryFunction implements Node {
	Function f;
	Node arg;
	public UnaryFunction(Function f, Node x) {
		this.f = f;
		arg = x;
	}
	
	@Override
	public double eval() throws Exception {
		return f.eval(arg.eval());
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", f.name, arg);
	}
}
