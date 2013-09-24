
public class Add implements ASTNode {
	ASTNode left, right;
	Add(ASTNode left, ASTNode right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()+right.eval();
	}
}
