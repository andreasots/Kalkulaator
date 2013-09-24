
public class UnaryFunction implements ASTNode {
	String func;
	ASTNode arg;
	UnaryFunction(String f, ASTNode x) {
		func = f;
		arg = x;
	}
	
	@Override
	public double eval() {
		switch (func) {
		case "abs":
			return Math.abs(arg.eval());
		case "acos":
			return Math.acos(arg.eval());
		case "asin":
			return Math.asin(arg.eval());
		case "atan":
			return Math.atan(arg.eval());
		case "cbrt":
			return Math.cbrt(arg.eval());
		case "ceil":
			return Math.ceil(arg.eval());
		case "cos":
			return Math.cos(arg.eval());
		case "cosh":
			return Math.cosh(arg.eval());
		case "exp":
			return Math.exp(arg.eval());
		case "expm1":
			return Math.expm1(arg.eval());
		case "floor":
			return Math.floor(arg.eval());
		case "getExponent":
			return Math.getExponent(arg.eval());
		case "log":
			return Math.log(arg.eval());
		case "log10":
			return Math.log10(arg.eval());
		case "log1p":
			return Math.log1p(arg.eval());
		case "nextUp":
			return Math.nextUp(arg.eval());
		case "rint":
			return Math.rint(arg.eval());
		case "round":
			return Math.round(arg.eval());
		case "signum":
			return Math.signum(arg.eval());
		case "sin":
			return Math.sin(arg.eval());
		case "sinh":
			return Math.sinh(arg.eval());
		case "sqrt":
			return Math.sqrt(arg.eval());
		case "tan":
			return Math.tan(arg.eval());
		case "tanh":
			return Math.tanh(arg.eval());
		case "toDegrees":
			return Math.toDegrees(arg.eval());
		case "toRadians":
			return Math.toRadians(arg.eval());
		case "ulp":
			return Math.ulp(arg.eval());
		default:
			throw new RuntimeException("Unknown unary function");
		}
	}
}
