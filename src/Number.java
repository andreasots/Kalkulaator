
public class Number implements ASTNode {
	double value;
	
	Number(String str) {
		value = Double.parseDouble(str);
	}
	
	@Override
	public double eval() {
		return value;
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
}
