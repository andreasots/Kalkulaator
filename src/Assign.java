import java.util.Map;

public class Assign implements ASTNode {
	Map<String, Function> names;
	String name;
	ASTNode value;
	
	public Assign(Map<String, Function> names, String name, ASTNode value) {
		this.names = names;
		this.name = name;
		this.value = value;
	}

	@Override
	public double eval() {
		final double x = value.eval();
		names.put(name, new Function(name) {
			double eval() {
				return x;
			}
		});
		return x;
	}
	
	@Override
	public String toString() {
		return String.format("%s=(%s)", name, value);
	}
}
