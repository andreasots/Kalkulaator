
public class Div implements ASTNode {
	ASTNode left, right;
	Div(ASTNode left, ASTNode right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()/right.eval();
	}

}
