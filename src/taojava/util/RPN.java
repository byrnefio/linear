package taojava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;

public class RPN {

    static LinkedStack<Double> vals;
    static PrintWriter pen = new PrintWriter(System.out, true);

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
	boolean terminate = false;
	vals = new LinkedStack<Double>();

	while (!terminate) {
	    // Take input
	    try {
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));
		// Split by whitespace
		String[] in = reader.readLine().split("\\s+");
		// Evaluate all the characters on the line 
		for (int i = 0; i < in.length; i++) {
		    terminate = eval(in[i]);

		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    /**
     * 
     * @param input
     * 		an input string representing a single operator or double
     * @return 
     * 		a boolean indicating whether the calculations are done
     * @throws Exception
     */
    public static boolean eval(String input) throws Exception {
	double first = 0;
	double second = 0;
	LinkedStackIterator<Double> it = new LinkedStackIterator<Double>(vals);
	
	if (input.equals("q")) {
	    return true;
	} else if (input.equals("p")) {
	    if (vals.isEmpty()) {
		pen.println("Stack empty");
	    } else {
		pen.println(vals.peek());
	    }
	} else if (input.equals("s")) {
	    while (it.hasNext()) {
		pen.println(it.next());
	    }
	} else if (input.equals("c")) {
	    vals = new LinkedStack<Double>();	    
	} else if (input.equals("+") || input.equals("/") || input.equals("*")
		|| input.equals("-") || input.equals("m")) {
	    try {
		first = vals.pop();
	    } catch (Exception e) {
		pen.println("Not enough items on stack");
		return false;
	    }
	    try {
		second = vals.pop();
	    } catch (Exception e) {
		vals.push(first);
		pen.println("Not enough items on stack");
		return false;
	    }
	    if (input.equals("+")) {
		vals.push(first + second);
	    } else if (input.equals("-")) {
		vals.push(first - second);
	    } else if (input.equals("/")) {
		vals.push(first / second);
	    } else if (input.equals("m")){
		vals.push(Math.max(first, second));
	    }else{
		vals.push(first * second);
	    }

	} else {
	    try {
		Double d = Double.parseDouble(input);
		vals.push(d);
	    } catch (Exception e) {
		pen.println("Unsupported operation: " + input);
	    }
	}

	return false;
    }
}
