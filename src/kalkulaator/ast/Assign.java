package kalkulaator.ast;
import java.util.Map;

import kalkulaator.parser.Function;

public class Assign implements Node {
	Map<String, Function> names;
	String name;
	Node value;
	
	public Assign(Map<String, Function> names, String name, Node value) {
		this.names = names;
		this.name = name;
		this.value = value;
	}

	@Override
	public double eval() throws Exception {
		final double x = value.eval();
		names.put(name, new Function(name, 0) {
			public double eval() {
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
