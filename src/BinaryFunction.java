
public class BinaryFunction implements ASTNode {
	Function f;
	ASTNode arg0, arg1;
	
	BinaryFunction(Function f, ASTNode x, ASTNode y) {
		this.f = f;
		arg0 = x;
		arg1 = y;
	}
	
	@Override
	public double eval() {
		return f.eval(arg0.eval(), arg1.eval());
	}

	@Override
	public String toString() {
		return String.format("%s (%s) (%s)", f.name, arg0, arg1);
	}
}
