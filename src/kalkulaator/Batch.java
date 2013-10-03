package kalkulaator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Batch {
	public static void main(String[] args) {
		Parser parser = new Parser();
	    BufferedReader input = null;
	    if (args.length == 0) {
	    	input = new BufferedReader(new InputStreamReader(System.in));
	    } else if (args.length == 1) {
			try {
				input = new BufferedReader(new FileReader(new File(args[0])));
			} catch (FileNotFoundException e1) {
				System.err.printf("%s: File not found\n", args[0]);
				return;
			}
		} else {
	    	System.err.printf("Usage: %s [script]\n", Batch.class.getName());
	    	return;
		}
	    
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
