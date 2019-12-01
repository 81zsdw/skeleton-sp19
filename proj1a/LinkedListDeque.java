import java.nio.file.attribute.AclEntry;

public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    public class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        /** create an empty linked list deque */
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        /** create a deep copy (different object than other) of other */
        Node node = other.sentinel;
        LinkedListDeque<T> copy = new LinkedListDeque();
        size = other.size;
        for (int i = 0; i < size; i++) {
            node = node.next;
            copy.addLast(node.item);
        }
        sentinel = copy.sentinel;
    }

    public void addFirst( T item) {
        /**Adds an item of type T to the front of the deque. */
        sentinel.next = new Node(sentinel , item, sentinel.next);
        sentinel.next.next.prev = sentinel.next; //link previous 1st node to new 1st node
        if (size == 0) {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast (T item) {
        /** Adds an item of type T to the back of the deque. */
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        if (size == 0) {
            sentinel.next = sentinel.prev;
        }
        size += 1;
    }

    public boolean isEmpty() {
        /** Returns true if deque is empty, false otherwise */
        return (size == 0);
    }

    public int size() {
        /** Returns the number of items in the deque.*/
        return size;
    }

    public void printDeque() {
        /**Prints the items in the deque from first to last,
         * separated by a space.
         * Once all the items have been printed,
         * print out a new line. */
        for (int i = 0; i < size; i++) {
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        /** Removes and returns the item at the front of the deque.
         * If no such item exists, returns null.*/
        if (sentinel.next == null) {
            return null;
        }
        T frontItem = sentinel.next.item;
        // remove
        sentinel.next = sentinel.next.next; //remove first node
        sentinel.next.prev = sentinel; // set prev of 2nd node to sentinel
        size -= 1;
        return frontItem;
    }

    public T removeLast() {
        /** Removes and returns the item at the back of the deque.
         * If no such item exists, returns null. */
        if (sentinel.prev == null) {
            return null;
        }
        T BackItem = sentinel.prev.item;
        // remove
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return BackItem;
    }

    public T get(int index) {
        /** must use iteration */
        if (index > size-1) {
            return null;
        }
        // get the sentinel address, copy by value
        Node this_node = sentinel;
        for (int i=0; i<=index; i++) {
            this_node = this_node.next;
        }
        return this_node.item;
    }

    public T getRecursive(int index) {
        /**  Gets the item at the given index,
         *  where 0 is the front, 1 is the next item, and so forth.
         *  If no such item exists, returns null.
         *  Must not alter the deque! */
        if (index > size-1) {
            return null;
        }
        // get address copy of centinel for recursion
        Node node = sentinel;
        return getHelper(node, index);
    }

    private T getHelper(Node node, int index) {
        /** Helper function for the get recursion,
         * in order to get multiple inputs*/
        if (index == 0) {
            return node.next.item;
        }
        return getHelper(node.next, index-1);
    }
}
