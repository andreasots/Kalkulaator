package kalkulaator.ast;
public class Variable implements Node {
	Function v;
	public Variable(Function v) {
		this.v = v;
	}
	
	@Override
	public double eval() {
		return v.eval();
	}

	@Override
	public String toString() {
		return v.name;
	}
}
