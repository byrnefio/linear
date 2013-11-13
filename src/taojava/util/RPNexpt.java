package taojava.util;

import java.io.PrintWriter;

public class RPNexpt {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);
	// Empty stack
	pen.print("Expected: Stack empty\n          ");
	RPN.parse("p", pen);
	// No items on stack, +
	pen.print("Expected: Not enough items on stack\n          ");
	RPN.parse("+ s", pen);
	// No items on stack, -
	pen.print("Expected: Not enough items on stack\n          ");
	RPN.parse("- s", pen);
	// No items on stack, *
	pen.print("Expected: Not enough items on stack\n          ");
	RPN.parse("* s", pen);
	// No items on stack, /
	pen.print("Expected: Not enough items on stack\n          ");
	RPN.parse("/ s", pen);
	// Pushing 5 to stack
	pen.print("Expected: 5.0\n          ");
	RPN.parse("5 s", pen);
	// One item on stack, +
	pen.println("Expected: Not enough items on stack \\n 5.0");
	RPN.parse("+ s", pen);
	// Pushing second element
	pen.println("Expected: 5.0 \\n 3.0");
	RPN.parse("3 s", pen);
	// Two elements, +
	pen.print("Expected: 8.0\n          ");
	RPN.parse("+ s", pen);
	// Two elements, -
	pen.print("Expected: 1.0\n          ");
	RPN.parse("7 - s", pen);
	// Two elements, *
	pen.print("Expected: 3.0\n          ");
	RPN.parse("3 * s", pen);
	// Two elements, /
	pen.print("Expected: -1.5\n          ");
	RPN.parse("-2 / s", pen);
	// Clear stack
	pen.println("Expected:");
	RPN.parse("c s", pen);
	// Pushing many numbers, peek
	pen.print("Expected: 46.0\n          ");
	RPN.parse("4 3 5 2 222.5 4.0 4.1 45 -2 46 p", pen);
	// Print the whole stack
	pen.println("Expected: 46.0 \\n -2.0 \\n 45.0 \\n 4.1 \\n "
		+ "4.0 \\n 222.5 \\n 2.0 \\n 5.0 \\n 3.0 \\n 4.0");
	RPN.parse("s", pen);
	// Clear, push twice, max
	pen.print("Expected: 6.0\n          ");
	RPN.parse("c 5 6 max s", pen);
	// Push second element, min
	pen.print("Expected: 2.0\n          ");
	RPN.parse("2 min s", pen);
	// Push second element, min incl. negative
	pen.print("Expected: -2.0\n          ");
	RPN.parse("-2 min s", pen);
	// Push second element, min of two negatives
	pen.print("Expected: -2.0\n          ");
	RPN.parse("-1 min s", pen);
	// Terminate
	pen.println("THE END");
	RPN.parse("1 2 q 3 p", pen);
    }

}
