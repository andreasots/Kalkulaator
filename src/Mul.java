
public class Mul implements ASTNode {
	ASTNode left, right;
	Mul(ASTNode left, ASTNode right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()*right.eval();
	}

}
