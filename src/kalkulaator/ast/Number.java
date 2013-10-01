package kalkulaator.ast;

public class Number implements Node {
	double value;
	
	public Number(String str) {
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
