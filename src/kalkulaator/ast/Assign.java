package kalkulaator.ast;

import kalkulaator.parser.Function;

public class Assign implements Node {
	Identifier name;
	Node value;
	
	public Assign(Node name, Node value) throws Exception {
		if (!(name instanceof Identifier))
			throw new Exception(String.format("Oodati sümbolit, leiti '%s'", name));
		this.name = (Identifier)name;
		this.value = value;
	}

	@Override
	public double eval() throws Exception {
		final double x = value.eval();
		name.names.put(name.name, new Function(name.name) {
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
