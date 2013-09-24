
public class Main {
	public static void main(String[] args) {
		String[] exprs = {"2", "2.0", ".2", "2+3", "2-3", "2*3", "2/3", "(2+3)/5", "2*(-3)"};
		
		Parser parser = new Parser();
		try {
			for (String expr : exprs)
				System.out.printf("%s = %f\n", expr, parser.parse(expr).eval());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
