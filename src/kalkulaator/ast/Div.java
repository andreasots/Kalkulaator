package kalkulaator.ast;

public class Div implements Node {
	Node left, right;
	public Div(Node left, Node right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() throws Exception {
		return left.eval()/right.eval();
	}
	
	@Override
	public String toString() {
		return String.format("(%s)/(%s)", left, right); 
	}

}
