
public class Sub implements ASTNode {
	ASTNode left, right;
	Sub(ASTNode left, ASTNode right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()-right.eval();
	}

}
