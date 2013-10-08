package kalkulaator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kalkulaator.ast.Node;

public class Interactive {
	public static void main(String[] args) {
		Parser parser = new Parser();
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    
	    System.out.print("Defineeritud sümbolid:");
	    for (String f : parser.names())
	    	System.out.print(" "+f);
	    System.out.println("");
	    
	    
	    // REPL
		while (true) {
			System.out.print(">>> ");
			String expr = null;
			try {
				expr = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (expr == null)
				break;
			
			Node tree;
			try {
				tree = parser.parse(expr);
			} catch (Exception e) {
				System.err.println("Viga: "+e.getMessage());
				e.printStackTrace();
				continue;
			}
			
			System.out.printf("%s = ", tree);
			try {
				System.out.printf("%.15f\n", tree.eval());
			} catch (Exception e) {
				System.out.println("Viga");
				System.err.println("Viga: "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
