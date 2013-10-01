package kalkulaator;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Batch {
	public static void main(String[] args) {
		Parser parser = new Parser();
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    
		while (true) {
			String expr = null;
			try {
				expr = input.readLine();
				if (expr == null)
					break;
				parser.parse(expr).eval();
			} catch (Exception e) {
				System.err.println("Error: "+e.getMessage());
			}			
		}
	}
}
