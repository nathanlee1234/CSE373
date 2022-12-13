package deques;

/**
 * A double-ended queue that allows addition, removal, and access to elements at either the front or
 * the back of the collection. The front of the deque contains the first element (aka "head") while
 * the back of the deque contains the last element (aka "tail"). Does not allow null elements, but
 * doesn't check for null elements.
 *
 * @param <T> the type of elements in this deque
 */
public interface Deque<T> {

    /**
     * Adds the given item to the front of this deque.
     *
     * @param item the element to add
     */
    void addFirst(T item);

    /**
     * Adds the given item to the back of this deque.
     *
     * @param item the element to add
     */
    void addLast(T item);

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @param index the index to get
     * @return the element at the given index
     */
    T get(int index);

    /**
     * Returns true if and only if this deque is empty.
     *
     * @return true if and only if this deque is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of items in this deque.
     *
     * @return the number of items in this deque
     */
    int size();

    /**
     * Removes and returns the item at the front of this deque. Returns null if the deque is empty.
     *
     * @return the item at the front of this deque, or null if the deque is empty
     */
    T removeFirst();

    /**
     * Removes and returns the item at the back of this deque. Returns null if the deque is empty.
     *
     * @return the item at the back of this deque, or null if the deque is empty
     */
    T removeLast();
}
