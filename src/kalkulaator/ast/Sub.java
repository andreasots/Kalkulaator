package kalkulaator.ast;

public class Sub implements Node {
	Node left, right;
	public Sub(Node left, Node right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()-right.eval();
	}

	@Override
	public String toString() {
		return String.format("(%s)-(%s)", left, right);
	}
}
