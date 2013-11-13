package taojava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;

public class RPN {
    /**
     * A flag indicating the user has attempted to terminate the REPL
     */
    static boolean terminate = false;
    
    /**
     * A stack of operands
     */
    static LinkedStack<Double> vals = new LinkedStack<Double>();

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);

	while (!terminate) {
	    try {
		// Take input
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));
		String input = reader.readLine();
		// Parse and evaluate input
		parse(input, pen);
	    } catch (IOException e) {
		e.printStackTrace();
	    }// try/catch
	}// while
    }// main

    /**
     * Parses a String of numbers and/or operators and passes them to the evaluator
     */
    public static void parse(String input, PrintWriter pen) throws Exception {
	// Split input at each whitespace character
	String[] in = input.split("\\s+");
	// Evaluate all the characters on the line
	for (int i = 0; i < in.length; i++) {
	    terminate = eval(in[i], pen);
	}// for
    }// parse

    /**
     * Evaluates an input, adding it to the stack or executing the appropriate operation
     * @param input
     *            an input string representing a single operator or double
     * @return a boolean indicating whether or not to terminate the program
     * @throws Exception
     */
    public static boolean eval(String input, PrintWriter pen) throws Exception {
	double first = 0;
	double second = 0;
	LinkedStackIterator<Double> it = new LinkedStackIterator<Double>(vals);

	// Test for special operations
	if (input.equals("q")) {
	    return true;
	} else if (input.equals("p")) {
	    if (vals.isEmpty()) {
		pen.println("Stack empty");
	    } else {
		pen.println(vals.peek());
	    }// if
	} else if (input.equals("s")) {
	    while (it.hasNext()) {
		pen.println(it.next());
	    }
	} else if (input.equals("c")) {
	    vals = new LinkedStack<Double>();
	    // Test for mathematical operators
	} else if (input.equals("+") || input.equals("/") || input.equals("*")
		|| input.equals("-") || input.equals("max") || input.equals("min")) {
	    try {
		first = vals.pop();
	    } catch (Exception e) {
		pen.println("Not enough items on stack");
		return false;
	    }// try/catch
	    try {
		second = vals.pop();
	    } catch (Exception e) {
		vals.push(first);
		pen.println("Not enough items on stack");
		return false;
	    }// try/catch
	    if (input.equals("+")) {
		vals.push(first + second);
	    } else if (input.equals("-")) {
		vals.push(first - second);
	    } else if (input.equals("/")) {
		vals.push(first / second);
	    } else if (input.equals("max")) {
		vals.push(Math.max(first, second));
	    } else if (input.equals("min")) {
		vals.push(Math.min(first, second));
	    } else {
		vals.push(first * second);
	    }// if
	} else {
	    // Assume input is a number, try to push it to stack
	    try {
		Double d = Double.parseDouble(input);
		vals.push(d);
	    } catch (Exception e) {
		// If parsing input as a double fails, alert user of error
		pen.println("Unsupported operation: " + input);
	    }// try
	}// if
	return false;
    }// eval
}// RPN
