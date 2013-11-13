package taojava.util;

import java.io.PrintWriter;

public class RPNexpt {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);
	pen.println("this should say Stack empty, because the stack is empty.");
	RPN.parse("p", pen);
	pen.println("There are no items on the stack. This should print out: Not enough Items on stack. It should then verify that the stack is empty");
	RPN.parse("+ s", pen);
	pen.println("There are no items on the stack. This should print out: Not enough Items on stack. It should then verify that the stack is empty");
	RPN.parse("/ s", pen);
	pen.println("There are no items on the stack. This should print out: Not enough Items on stack. It should then verify that the stack is empty");
	RPN.parse("* s", pen);
	pen.println("There are no items on the stack. This should print out: Not enough Items on stack. It should then verify that the stack is empty");
	RPN.parse("- s", pen);
	pen.println("We're adding one thing to the stack. It is the number 5. We'll then print the stack to show that only 5 is on the stack.");
	RPN.parse("5 s", pen);
	pen.println("There is one item on the stack. This should print out: Not enough Items on stack. It should then verify that the stack only contains 5");
	RPN.parse("+ s", pen);
	pen.println("We're adding one thing to the stack. It is the number 6. We'll then print the stack to show that only 5 and 6 are on the stack.");
	RPN.parse("6  s", pen);
	pen.println("We'll now add those numbers. The result should be 11. When we print the stack, only the number 11 should be on the stack.");
	RPN.parse("+ s", pen);
	pen.println("We'll now add an additional number, -2, and divide the two numbers on the stack. we'll then print the stack.");
	RPN.parse("-2 / s",pen);
	pen.println("we'll now clear the stack, and print the whole stack to show that it is cleared.");
	RPN.parse("c s",pen);
	pen.println("We'll now add a bunch of things to the stack, and only print the top one.");
	RPN.parse("4 3 5 2 222 4 4 45 -2 46 p",pen);
	pen.println("Now we'll print the whole stack");
	RPN.parse("s",pen);
	pen.println("Finally we're going to clear the stack, and add 5 and 6 to the stack, calculating the greater number, and then print the stack to show that the greater number has been pushed back to the stack.");
	RPN.parse("c 5 6 max p",pen);

    }

}
