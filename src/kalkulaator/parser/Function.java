package kalkulaator.parser;
public class Function {
	public String name;
	public int args;
	
	public Function(String f, int a) {
		name = f;
		args = a;
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
