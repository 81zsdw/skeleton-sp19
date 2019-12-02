import java.lang.reflect.Array;

public class ArrayDeque<T> {
    /** This class uses array as core data structure
     * - Treat array as circular, so front pointer will loop back around
     * to the end of array
     * - for arrays of length 16 or more, usage factor should be at least 25%
     * For smaller arrays, usage factor can be arbitrarily low.
     * Development Strategy:
     * 1 functionality
     * 2 add in resizing will be performed after code works
     */
    private T[] items;
    private int nextFirst; //index of next time executing addFirst
    private int nextLast; //index of next time executing addLast
    private int size;

    private static int minArraySizeDownSize = 16;
    private static double usageFactorThreshold = 0.25;

    public ArrayDeque() {
        /** Constructor to create an empty array deque. */
        items = (T[]) new Object[8];
        nextFirst = 4; nextLast = 5; //4 and 5 randomly picked
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        /** Creating a deep copy means that you create an entirely new ArrayDeque,
         * with the exact same items as other.
         * However, they should be different objects,
         * i.e. if you change other,
         * the new ArrayDeque you created should not change as well.*/
        /** approach 1:
         * loop through to get values according to actual indices order
         * reorder them to start from 0 to size, with 3 x empty spaces
        size = other.size();
        items = (T[]) new Object[size * 4];
        //for simplicity set the current first index to be 0, for new array
        nextFirst = minusOne(0);
        nextLast = size;
        for (int i = 0; i < size; i++) {
            items[i] = (T) other.get(i);
        }
         */

        //approach 2:
        size = other.size();
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    private void runResizingAgainstUsageFactor() {
        /** helper function to check if resizing is needed */
        //check if need to size up, if array is full
        float usageFactor = ((float)size) / items.length;
        if (usageFactor == 1) {
            resize(size * 2);
        }
        // don't care about sizing down for small arrays
        if (items.length > minArraySizeDownSize) {
            if (usageFactor < usageFactorThreshold) {
                // even size down, the new capacity should still double current size
                // so the usage factor is 0.5
                resize(size * 2);
            }
        }
    }

    private void resize(int capacity) {
        /** helper function to resize the current array deque
         * here we consider both size up and size down */
        T[] copy = (T[]) new Object[capacity];
        // criterion for whether array loops around
        if (plusOne(nextFirst) < minusOne(nextLast)) {
            // current first index < last index, no loop, copy once
            System.arraycopy(items, plusOne(nextFirst),
                             copy, 0, size);
        } else {
            // first item starts somewhere middle, then loop around
            // need copy twice
            int first_half_length = items.length - plusOne(nextFirst);
            int second_half_length = size - first_half_length;
            System.arraycopy(items, plusOne(nextFirst), copy,
                    0, first_half_length);
            System.arraycopy(items, 0, copy,
                    first_half_length, second_half_length);
        }
        items = copy;
        // b/c new array starts from index 0
        // update nextFirst to final index
        // size should not change, just array length change
        nextFirst = items.length - 1;
        nextLast = size;
        copy = null;  // to avoid loitering
    }

    private int minusOne(int index) {
        /** helper function to get index-1 for circular array */
        if (index == 0) {
            return (items.length-1);
        }
        return index-1;
    }

    private int plusOne(int index) {
        /** helper function to get index+1 for circular array */
        if (index == items.length-1) {
            return 0;
        }
        return (index + 1);
    }

    public void addFirst (T item) {
        /**Adds an item of type T to the front of the deque. */
        runResizingAgainstUsageFactor();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        /** Adds an item of type T to the back of the deque. */
        runResizingAgainstUsageFactor();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
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
        for (int i = 0; i < size;i ++) {
            System.out.print(get(i)+" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        /** Removes and returns the item at the front of the deque.
         * If no such item exists, returns null.*/
        if (size == 0) {
            return null;
        }
        // update nextFirst to current front item position
        nextFirst = plusOne(nextFirst);
        size -= 1;
        // no need to apply null to removed item,
        // will be over-written with addFirst
        // finally resize
        runResizingAgainstUsageFactor();
        return items[nextFirst];
    }

    public T removeLast() {
        /** Removes and returns the item at the back of the deque.
         * If no such item exists, returns null. */
        if (size == 0) {
            return null;
        }
        // update nextLast to current last item position
        nextLast = minusOne(nextLast);
        size -= 1;
        // no need to apply null to removed item,
        // will be over-written with addLast
        // finally resize
        runResizingAgainstUsageFactor();
        return items[nextLast];
    }

    public T get(int index) {
        /** Gets the item at the given index
         * where 0 is the front, 1 is the next item, and so forth.
         * If no such item exists, returns null.
         * Must not alter the deque! */
        if (index > size-1) {
            return null;
        }
        // check actual array index against boundary
        int arrayIndex = nextFirst + 1 + index;
        if (arrayIndex > items.length-1) {
            arrayIndex -= items.length;
        }
        return items[arrayIndex];
    }
}
