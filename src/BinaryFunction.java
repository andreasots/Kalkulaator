
public class BinaryFunction implements ASTNode {
	String name;
	ASTNode arg0, arg1;
	
	BinaryFunction(String f, ASTNode x, ASTNode y) {
		name = f;
		arg0 = x;
		arg1 = y;
	}
	
	@Override
	public double eval() {
		switch (name) {
		case "atan2":
			return Math.atan2(arg0.eval(), arg1.eval());
		case "copySign":
			return Math.copySign(arg0.eval(), arg1.eval());
		case "hypot":
			return Math.hypot(arg0.eval(), arg1.eval());
		case "IEEEremainder":
			return Math.IEEEremainder(arg0.eval(), arg1.eval());
		case "max":
			return Math.max(arg0.eval(), arg1.eval());
		case "min":
			return Math.min(arg0.eval(), arg1.eval());
		case "nextAfter":
			return Math.nextAfter(arg0.eval(), arg1.eval());
		case "pow":
			return Math.pow(arg0.eval(), arg1.eval());
		case "scalb":
			return Math.scalb(arg0.eval(), (int) arg1.eval());
		default:
			throw new RuntimeException("Unknown binary function: "+name);
		}
	}

	@Override
	public String toString() {
		return String.format("%s (%s) (%s)", name, arg0, arg1);
	}
}
