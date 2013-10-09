package kalkulaator.ast;

import kalkulaator.parser.Function;

public class Variable implements Node {
	Function v;
	public Variable(Function v) {
		this.v = v;
	}
	
	@Override
	public double eval() throws Exception {
		return v.eval();
	}

	@Override
	public String toString() {
		return v.name+"()";
	}
}
