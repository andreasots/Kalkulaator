public class Variable implements ASTNode {
	Function v;
	Variable(Function v) {
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
