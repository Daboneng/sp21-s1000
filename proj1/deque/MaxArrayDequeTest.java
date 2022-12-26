package deque;


import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        Comparator<String> c = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(c);

        assertTrue("A newly initialized LLDeque should be empty", mad1.isEmpty());
        mad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, mad1.size());
        assertFalse("mad1 should now contain 1 item", mad1.isEmpty());

        mad1.addLast("middle");
        assertEquals(2, mad1.size());

        mad1.addLast("back");
        assertEquals(3, mad1.size());

        System.out.println("Printing out deque: ");
        mad1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(c);

        // should be empty
        assertTrue("mad1 should be empty upon initialization", mad1.isEmpty());

        mad1.addFirst(10);
        // should not be empty
        assertFalse("mad1 should contain 1 item", mad1.isEmpty());

        mad1.removeFirst();
        // should be empty
        assertTrue("mad1 should be empty after removal", mad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(c);

        mad1.addFirst(3);

        mad1.removeLast();
        mad1.removeFirst();
        mad1.removeLast();
        mad1.removeFirst();

        int size = mad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }


    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(c);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, mad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, mad1.removeLast());

    }

}
