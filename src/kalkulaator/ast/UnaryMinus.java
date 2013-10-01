package kalkulaator.ast;

public class UnaryMinus implements Node {
	private Node node;
	
	public UnaryMinus(Node node) {
		this.node = node;
	}
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		return -node.eval();
	}

	@Override
	public String toString() {
		return String.format("-(%s)", node);
	}
}
