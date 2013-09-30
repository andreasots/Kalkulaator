public class Function {
	String name;
	
	Function(String f) {
		name = f;
	}
	
	double eval() {
		throw new RuntimeException(String.format("'%s' is not a variable", name));
	}
	
	double eval(double x) {
		throw new RuntimeException(String.format("'%s' is not a unary function", name));
	}
	
	double eval(double x, double y) {
		throw new RuntimeException(String.format("'%s' is not a binary function", name));
	}
}
