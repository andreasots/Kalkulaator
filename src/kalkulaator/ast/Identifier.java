package kalkulaator.ast;

import java.util.Map;

import kalkulaator.parser.Function;

public class Identifier implements Node {
	public String name;
	public Map<String, Function> names;

	public Identifier(Map<String, Function> names, String name) {
		this.names = names;
		this.name = name;
	}

	@Override
	public double eval() throws Exception {
		Function f = names.get(name);
		if (f == null)
			throw new Exception("Defineerimata sümbol");
		return f.eval();
	}

	@Override
	public String toString() {
		return name;
	}
}
