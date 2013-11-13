package taojava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;

/**
 * A reverse Polish notation calculator that utilizes stacks
 * 
 * @author Justus Goldstein-Shirley
 * @author Fiona Byrne
 * @author Adam Arsenault
 * @author Kitt Nika
 */
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
     * Runs a REPL for the calculator
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);

	while (!terminate) { // Until the user inputs "q"
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
     * Parses a String of numbers and/or operators and passes them to the
     * evaluator
     * 
     * @param input
     *            a String containing numbers and operators, separated by
     *            whitespace
     * @param pen
     *            a PrintWriter
     * @pre vals, a stack of Doubles, exists.
     * @post eval(String, PrintWriter) has been called on every substring of
     *       input, separated by whitespace characters, until eval returns true.
     * @post if eval returns true, set terminate to true.
     * @throws Exception
     *             If eval throws an Exception.
     */
    public static void parse(String input, PrintWriter pen) throws Exception {
	// Split input at each whitespace character
	String[] in = input.split("\\s+");
	// Evaluate all the characters on the line
	for (int i = 0; i < in.length; i++) {
	    if (!terminate) {
		terminate = eval(in[i], pen);
	    }
	}// for
    }// parse

    /**
     * Evaluates an input, adding it to the stack or executing the appropriate
     * operation
     * 
     * @param input
     *            a String representing a single operator or double
     * @param pen
     *            a PrintWriter
     * @return a boolean indicating whether or not to terminate the program
     * @pre vals, a stack of Doubles, exists.
     * @post If input can be parsed as a double, it is pushed to vals.
     * @post If input is a valid command, it is executed.
     * @post If input is a valid operator and there are at least two elements on
     *       the stack, the top two elements are popped off the stack, and the
     *       result of applying the operator to them is pushed to the stack.
     * @post If input is a command or operator that cannot be executed, print
     *       error message to pen and do not alter the stack.
     * @post If input is not a valid number, command, or operator, print error
     *       message to pen and do not alter the stack.
     * @post If input is "q", returns true. Else, returns false.
     * @throws Exception
     *             If the stack throws an Exception.
     * @note Valid input Strings include:
     * @input a real number - push the number to the stack
     * @input "+", "-", "*", and "/" - apply the operator to the top two
     *        elements of the stack
     * @input "max" - push the higher of the top two elements
     * @input "min" - push the lower of the top two elements
     * @input "p" - print the top value on the stack
     * @input "s" - print the whole stack
     * @input "c" - clear the stack
     * @input "q" - quit the program; that is, return true
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
		|| input.equals("-") || input.equals("max")
		|| input.equals("min")) {
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
	    // Assume input is a number and try to push it to stack
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
