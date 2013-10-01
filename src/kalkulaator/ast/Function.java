package kalkulaator.ast;
public class Function {
	String name;
	
	public Function(String f) {
		name = f;
	}
	
	public double eval() {
		throw new RuntimeException(String.format("'%s' is not a variable", name));
	}
	
	public double eval(double x) {
		throw new RuntimeException(String.format("'%s' is not a unary function", name));
	}
	
	public double eval(double x, double y) {
		throw new RuntimeException(String.format("'%s' is not a binary function", name));
	}
}
