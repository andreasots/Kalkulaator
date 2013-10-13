package kalkulaator.ast;

public class Conditional implements Node {
	private Node cond, branch1, branch2;
	
	public Conditional(Node cond, Node branch1, Node branch2) {
		this.cond = cond;
		this.branch1 = branch1;
		this.branch2 = branch2;
	}

	@Override
	public double eval() throws Exception {
		if (Math.abs(cond.eval()) < 1E-15)
			return branch2.eval();
		else
			return branch1.eval();
	}
	
	@Override
	public String toString() {
		return String.format("(%s) ? (%s) : (%s)", cond, branch1, branch2);
	}
}
