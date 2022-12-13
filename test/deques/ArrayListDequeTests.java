package deques;

/**
 * Tests for the {@link ArrayListDeque} class.
 *
 * @see ArrayListDeque
 */
public class ArrayListDequeTests extends DequeTests {
    @Override
    public <T> Deque<T> createDeque() {
        return new ArrayListDeque<T>() {

        };
    }

    // You may write additional tests here if you only want them to run for LinkedDeque
}
