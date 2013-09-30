
public class UnaryMinus implements ASTNode {
	private ASTNode node;
	
	UnaryMinus(ASTNode node) {
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
