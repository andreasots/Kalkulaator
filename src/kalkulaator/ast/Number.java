package kalkulaator.ast;

public class Number implements Node {
	double value;
	
	public Number(double d) {
		value = d;
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
