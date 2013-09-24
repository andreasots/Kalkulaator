import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser();
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    
	    // REPL
		while (true) {
			System.out.print("> ");
			String expr = null;
			try {
				expr = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (expr == null)
				break;
			
			try {
				System.out.println(parser.parse(expr).eval());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
