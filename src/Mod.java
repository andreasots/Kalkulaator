
public class Mod implements ASTNode {
	ASTNode left, right;
	Mod(ASTNode left, ASTNode right) {
		this.left = left; 
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval()%right.eval();
	}

}
