package kalkulaator.ast;

import kalkulaator.parser.Function;

public class BinaryFunction implements Node {
	Function f;
	Node arg0, arg1;
	
	public BinaryFunction(Function f, Node x, Node y) {
		this.f = f;
		arg0 = x;
		arg1 = y;
	}
	
	@Override
	public double eval() throws Exception {
		return f.eval(arg0.eval(), arg1.eval());
	}

	@Override
	public String toString() {
		return String.format("%s(%s, %s)", f.name, arg0, arg1);
	}
}
