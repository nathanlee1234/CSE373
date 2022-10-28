package minpq;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link ExtrinsicMinPQ} interface.
 *
 * @param <T> the type of elements in this priority queue.
 * @see ExtrinsicMinPQ
 */
public class UnsortedArrayMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the item-priority pairs in no specific order.
     */
    private final List<PriorityNode<T>> items;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        items = new ArrayList<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        items.add(new PriorityNode<>(item, priority));
    }

    @Override
    public boolean contains(T item) {
        for (PriorityNode i : items) {
            if (i.item() == item) return true;
        }
        return false;
    }

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        PriorityNode<T> min = items.get(0);
        for (PriorityNode i : items) {
            if (i.priority() < min.priority()) min = i;
        }
        return min.item();
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        PriorityNode<T> min = items.get(0);
        for (PriorityNode i : items) {
            if (i.priority() < min.priority()) min = i;
        }
        items.remove(min);
        return min.item();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        for (PriorityNode i : items) {
            if (i.item() == item) i.setPriority(priority);
        }
    }

    @Override
    public int size() {
        return items.size();
    }
}
