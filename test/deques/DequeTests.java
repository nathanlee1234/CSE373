package deques;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class providing test cases for all implementations of the {@link Deque} interface.
 *
 * @see Deque
 */
public abstract class DequeTests {
    /**
     * Returns an empty {@link Deque}.
     *
     * @param <T> the type of elements in the returned deque
     * @return an empty deque
     */
    public abstract <T> Deque<T> createDeque();

    @Test
    void sizeWhenEmptyIsZero() {
        Deque<String> deque = createDeque();
        assertTrue(deque.isEmpty());
    }

    @Test
    void getIndexZeroWhenEmptyReturnsNull() {
        Deque<String> deque = createDeque();
        assertNull(deque.get(0));
    }

    @Test
    void getNegativeIndexWhenEmptyReturnsNull() {
        Deque<String> deque = createDeque();
        assertNull(deque.get(-1));
    }

    @Test
    void getPositiveIndexWhenEmptyReturnsNull() {
        Deque<String> deque = createDeque();
        assertNull(deque.get(1));
    }

    @Test
    void usingMultipleDequesSimultaneouslyDoesNotCauseInterference() {
        Deque<Integer> d1 = createDeque();
        Deque<Integer> d2 = createDeque();
        d1.addFirst(1);
        d2.addFirst(2);
        d1.addFirst(3);
        assertEquals(2, d1.size());
        assertEquals(1, d2.size());
    }

    @Test
    void addToEmptyDoesNotThrowException() {
        Deque<String> deque = createDeque();
        deque.addFirst("s");
    }

    @Test
    void sizeWithOneItemIsOne() {
        Deque<String> deque = createDeque();
        deque.addFirst("s");
        assertEquals(1, deque.size());
    }

    @Test
    void getIndexZeroWithOneItemReturnsItem() {
        Deque<String> deque = createDeque();
        deque.addFirst("s");
        String output = deque.get(0);
        assertEquals("s", output);
    }

    @Test
    void removeAfterAddOneItemToSameSideReturnsItem() {
        Deque<String> deque = createDeque();
        deque.addFirst("s");
        String output = deque.removeFirst();
        assertEquals("s", output);
    }

    @Test
    void removeAfterAddOneItemToOppositeSideReturnsItem() {
        Deque<String> deque = createDeque();
        deque.addFirst("s");
        String output = deque.removeLast();
        assertEquals("s", output);
    }

    @Test
    void removeWhenEmptyReturnsNull() {
    Deque<String> deque = createDeque();
        String output = deque.removeFirst();
        assertNull(output);
    }

    @Test
    void getEachAfterAddToOppositeEndsReturnsCorrectItems() {
        Deque<String> deque = createDeque();
        deque.addFirst("a");
        deque.addLast("b");
        assertEquals("a", deque.get(0));
        assertEquals("b", deque.get(1));
        assertEquals(2, deque.size());
    }
    @Test
    void newTest() {
        Deque<String> deque = createDeque();
        deque.addLast("a");
        assertEquals("a", deque.get(0));
    }

    @Test
    void sizeAfterAddToOppositeEndsIsTwo() {
        Deque<String> deque = createDeque();
        deque.addFirst("a");
        deque.addLast("b");
        assertEquals(2, deque.size());
    }

    @Test
    void removeAfterAddToOppositeEndsReturnsCorrectItem() {
        Deque<String> deque = createDeque();
        deque.addFirst("a");
        deque.addLast("b");
        String output = deque.removeFirst();
        assertEquals("a", output);
    }

    @Test
    void sizeAfterRemoveAfterAddToOppositeEndsIsOne() {
        Deque<String> deque = createDeque();
        deque.addFirst("a");
        deque.addLast("b");
        deque.removeFirst();
        assertEquals(1, deque.size());
    }

    @Test
    void removeAfterRemoveAfterAddToOppositeEndsReturnsCorrectItem() {
        Deque<String> deque = createDeque();
        deque.addFirst("a");
        deque.addLast("b");
        deque.removeFirst();
        String output = deque.removeLast();
        assertEquals("b", output);
    }

    @Test
    void getEachAfterAddManyToSameSideReturnsCorrectItems() {
        int numElements = 20;
        Deque<Integer> deque = createDeque();
        for (int i = 0; i < numElements; i += 1) {
            deque.addLast(i);
        }
        for (int i = 0; i < numElements; i += 1) {
            assertEquals(i, deque.get(i));
        }
        assertEquals(numElements, deque.size());
    }

    @Test
    void removeAfterAddStringReturnsCorrectString() {
        Deque<String> deque = createDeque();
        deque.addFirst("string");
        String s = deque.removeFirst();
        assertEquals("string", s);
    }
    @Test
    void removeAfterAddDoubleReturnsCorrectDouble() {
        Deque<Double> deque = createDeque();
        deque.addFirst(3.1415);
        double d = deque.removeFirst();
        assertEquals(3.1415, d);
    }

    @Test
    void removeAfterAddBooleanReturnsCorrectBoolean() {
        Deque<Boolean> deque = createDeque();
        deque.addFirst(true);
        boolean b = deque.removeFirst();
        assertTrue(b);
    }

    /**
     * This test demonstrates why we like unit tests that test a single unit at a time: calling
     * multiple methods in the same test makes issues very hard to debug.
     */
    @Test
    void confusingTest() {
        Deque<Integer> deque = createDeque();
        deque.addFirst(0);
        assertEquals(0, deque.get(0));

        deque.addLast(1);
        assertEquals(1, deque.get(1));

        deque.addFirst(-1);
        deque.addLast(2);
        assertEquals(2, deque.get(3));

        deque.addLast(3);
        deque.addLast(4);

        // Test that removing and adding back is okay
        assertEquals(-1, deque.removeFirst());
        deque.addFirst(-1);
        assertEquals(-1, deque.get(0));

        deque.addLast(5);
        deque.addFirst(-2);
        deque.addFirst(-3);

        // Test a tricky sequence of removes
        assertEquals(-3, deque.removeFirst());
        assertEquals(5, deque.removeLast());
        assertEquals(4, deque.removeLast());
        assertEquals(3, deque.removeLast());
        assertEquals(2, deque.removeLast());

        // TODO ArrayDeque fails here; write better tests to help you find and fix the bug
        int actual = deque.removeLast();
        assertEquals(1, actual);
    }

    @Nested
    class RuntimeExperiments {
        /**
         * Number of trials per implementation run. Making this smaller means experiments run faster.
         */
        public static final int NUM_TRIALS = 1000;
        /**
         * Maximum number of elements to add.
         */
        public static final int MAX_SIZE = 20000;
        /**
         * Step size increment. Making this smaller means experiments run slower.
         */
        public static final int STEP = 100;

        /**
         * Print the time in seconds that it takes to add and get elements from a deque for
         * increasingly-large deques of integers. The output is given as comma-separated values with
         * columns deque size, add time (seconds), and get time (seconds).
         */
        @Test
        void addLastGetMiddle() {
            for (int size = STEP; size <= MAX_SIZE; size += STEP) {
                System.out.print(size);
                System.out.print(',');

                // Record the total runtimes accumulated across all trials
                double totalAddTime = 0.0;
                double totalGetTime = 0.0;

                for (int i = 0; i < NUM_TRIALS; i += 1) {
                    Deque<Integer> deque = createDeque();

                    // Measure the time to add integers
                    long addStart = System.nanoTime();
                    for (int j = 0; j < size; j += 1) {
                        deque.addLast(j);
                    }
                    long addTime = System.nanoTime() - addStart;
                    // Convert from nanoseconds to seconds and add to total time
                    totalAddTime += (double) addTime / 1_000_000_000;

                    // Measure the time to get the item at the given index
                    long getStart = System.nanoTime();
                    deque.get(size / 2);
                    long getTime = System.nanoTime() - getStart;
                    totalGetTime += (double) getTime / 1_000_000_000;
                }

                // Output the averages to 10 decimal places.
                System.out.printf("%.10f", totalAddTime / NUM_TRIALS);
                System.out.print(',');
                System.out.printf("%.10f", totalGetTime / NUM_TRIALS);
                System.out.println();
            }
        }
    }
}
