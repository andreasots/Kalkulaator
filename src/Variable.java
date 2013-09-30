public class Variable implements ASTNode {
	String name;
	Variable(String name) {
		this.name = name;
	}
	
	@Override
	public double eval() {
		switch (name) {
		case "pi":
			return Math.PI;
		case "e":
			return Math.E;
		default:
			throw new RuntimeException("Undeclared variable: "+name);
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
