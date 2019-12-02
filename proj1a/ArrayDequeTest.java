public class ArrayDequeTest {
    public static void addRemoveGetTest() {
        /** basic functionality test without resizing in place */
        System.out.println("\nRunning add, remove, get test.\n");

        ArrayDeque<String> ad1 = new ArrayDeque();
        if (ad1.isEmpty()) {
            System.out.println("passed is empty test!");
        } else {
            System.out.println("FAILED is empty test!");
        }

        ad1.addFirst("a"); ad1.addFirst("b");
        ad1.addFirst("c"); ad1.addFirst("d");
        ad1.addLast("eee"); ad1.addFirst("asdf");
        ad1.addLast("asdffdda");

        if (ad1.size() == 7) {
            System.out.println("passed size test!");
        } else {
            System.out.println("FAILED size test!");
        }
        System.out.print("The array deque is: ");
        ad1.printDeque();


        if (ad1.get(3) == "b") {
            System.out.println("passed get test");
        } else {
            System.out.println("FAILED get test");
        }

        if (ad1.get(6) == "asdffdda") {
            System.out.println("passed get test");
        } else {
            System.out.println("FAILED get test");
        }

        if (ad1.get(13) == null) {
            System.out.println("passed get test - null");
        } else {
            System.out.println("FAILED get test - null");
        }

        if (ad1.removeFirst() == "asdf") {
            System.out.println("passed removeFirst test");
        } else {
            System.out.println("FAILED removeFirst test");
        }
        System.out.print("Remove first, array deque is: ");
        ad1.printDeque();

        if ( ad1.removeLast() == "asdffdda") {
            System.out.println("passed removeLast test");
        } else {
            System.out.println("FAILED removeLast test");
        }
        System.out.print("Remove last, array deque is: ");
        ad1.printDeque();

        ad1.removeLast();
        System.out.print("Remove last, array deque is: ");
        ad1.printDeque();

        if (ad1.size() == 4) {
            System.out.println("passed size test!");
        } else {
            System.out.println("FAILED size test!");
        }
    }

    public static void deepCopyTest() {
        System.out.println("\nRunning deep copy test\n");
        ArrayDeque<String> ad2 = new ArrayDeque();
        ad2.addFirst("aa");
        ad2.addLast("bb");
        ad2.addLast("cc");
        ad2.addFirst("zz");
        System.out.print("The original array deque is: ");
        ad2.printDeque();
        ArrayDeque<String> ad_copy = new ArrayDeque(ad2);
        System.out.print("The copy array deque is: ");
        ad_copy.printDeque();
        ad2.removeLast();
        ad2.removeFirst();
        System.out.print("After removal, the original array deque is: ");
        ad2.printDeque();
        System.out.print("The copy array deque (should not change) is: ");
        ad_copy.printDeque();
    }

    public static void sizeUpTest() {
        System.out.println("\nRunning resizing up test.\n");
        ArrayDeque<String> ad2 = new ArrayDeque();
        ad2.addFirst("aa");
        ad2.addLast("bb");
        ad2.addLast("cc");
        ad2.addLast("dd");
        ad2.addLast("ee");
        ad2.addLast("ff");
        ad2.addLast("gg");
        ad2.addLast("hh");
        System.out.print("array deque: ");
        ad2.printDeque();
        ad2.addLast("ii");
        System.out.print("array deque: ");
        ad2.printDeque();
    }

    public static void sizeDownTest() {
        System.out.println("\nRunning resizing down test\n");
        ArrayDeque<Integer> ad2 = new ArrayDeque();
        for (int i = 0; i < 5000; i++) {
            ad2.addLast(i);
        }
        System.out.print("array deque: ");
        ad2.printDeque();
        System.out.println("with size: "+ ad2.size());
        // remove 4500 items
        for (int i = 0; i < 2250; i++) {
            ad2.removeLast();
            ad2.removeFirst();
        }
        System.out.print("array deque: ");
        ad2.printDeque();
        System.out.println("with size: "+ ad2.size());
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addRemoveGetTest();
        deepCopyTest();
        sizeUpTest();
        sizeDownTest();
    }
}
