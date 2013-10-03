package kalkulaator.ast;
public class Function {
	String name;
	
	public Function(String f) {
		name = f;
	}
	
	public double eval() {
		throw new RuntimeException(String.format("'%s' ei ole muutuja", name));
	}
	
	public double eval(double x) {
		throw new RuntimeException(String.format("'%s' ei ole ühe argumendiga funktsioon", name));
	}
	
	public double eval(double x, double y) {
		throw new RuntimeException(String.format("'%s' ei ole kahe argumendiga funktsioon", name));
	}
}
