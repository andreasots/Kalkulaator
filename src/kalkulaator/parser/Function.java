package kalkulaator.parser;
public class Function {
	public String name;
	
	public Function(String f) {
		name = f;
	}
	
	public double eval() throws Exception {
		throw new Exception(String.format("'%s' ei ole muutuja", name));
	}
	
	public double eval(double x) throws Exception {
		throw new Exception(String.format("'%s' ei ole ühe argumendiga funktsioon", name));
	}
	
	public double eval(double x, double y) throws Exception {
		throw new Exception(String.format("'%s' ei ole kahe argumendiga funktsioon", name));
	}
}
