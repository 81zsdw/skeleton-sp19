import javax.naming.LinkLoopException;
import java.util.LinkedList;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();
		String s = lld1.get(1);
		String s2 = lld1.getRecursive(1);
		System.out.println("get index 1 using iteration: "+s);
		System.out.println("get index 1 using recursion: "+s2);
		if ((s == s2) && (s == "middle")) {
			System.out.println("Passed get and getRecursion");
		} else {
			passed = false;
		}
		System.out.print("print the deque again to ensure no change:");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		// add more complex test to add/remove front/last
		lld1.addFirst(15);
		lld1.addFirst(20);
		lld1.addLast(35);
		lld1.addFirst(-15);
		lld1.addFirst(220);
		lld1.addLast(354);
		System.out.print("current deque is: ");
		lld1.printDeque();
		lld1.removeFirst();
		System.out.print("after remove first, deque is: ");
		lld1.printDeque();
		lld1.removeLast();
		System.out.print("after remove last, deque is: ");
		lld1.printDeque();
		lld1.removeLast();
		System.out.print("after remove last, deque is: ");
		lld1.printDeque();
		lld1.removeFirst();
		System.out.print("after remove first, deque is: ");
		lld1.printDeque();
		lld1.removeFirst();
		System.out.print("after remove first, deque is: ");
		lld1.printDeque();

		printTestStatus(passed);
	}

	public static void deepCopyTest() {
		/** test the deep copy initialization method */
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addFirst(15);
		lld1.addFirst(10);
		lld1.addFirst(415);
		lld1.addFirst(130);
		lld1.addLast(145);
		lld1.addLast(120);
		lld1.addLast(4135);
		lld1.addLast(1350);
		LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(lld1);
		System.out.print("current deque1: ");
		lld1.printDeque();
		System.out.print("current deque2: ");
		lld2.printDeque();
		System.out.print("After deep copy, current deque1 (should not change): ");
		lld1.printDeque();
		lld1.removeFirst();
		lld1.removeFirst();
		lld1.removeLast();
		System.out.print("after remove first x2 then last, current deque1: ");
		lld1.printDeque();
		System.out.print("current deque2 (should not change): ");
		lld2.printDeque();
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		deepCopyTest();
	}
} 