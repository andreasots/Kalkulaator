package kalkulaator.ast;

public class UnaryFunction implements Node {
	Function f;
	Node arg;
	public UnaryFunction(Function f, Node x) {
		this.f = f;
		arg = x;
	}
	
	@Override
	public double eval() {
		return f.eval(arg.eval());
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", f.name, arg);
	}
}
