
public class UnaryFunction implements ASTNode {
	Function f;
	ASTNode arg;
	UnaryFunction(Function f, ASTNode x) {
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
