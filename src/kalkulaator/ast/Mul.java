package kalkulaator.ast;

public class Mul implements Node {
	Node left, right;
	public Mul(Node left, Node right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() throws Exception {
		return left.eval()*right.eval();
	}

	@Override
	public String toString() {
		return String.format("(%s)*(%s)", left, right);
	}
}
