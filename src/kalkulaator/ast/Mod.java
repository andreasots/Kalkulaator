package kalkulaator.ast;

public class Mod implements Node {
	Node left, right;
	public Mod(Node left, Node right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() throws Exception {
		return left.eval()%right.eval();
	}

	@Override
	public String toString() {
		return String.format("(%s)%%(%s)", left, right);
	}
}
