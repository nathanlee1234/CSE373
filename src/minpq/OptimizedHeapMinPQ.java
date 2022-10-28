package minpq;

import java.util.*;

/**
 * Optimized binary heap implementation of the {@link ExtrinsicMinPQ} interface.
 *
 * @param <T> the type of elements in this priority queue.
 * @see ExtrinsicMinPQ
 */
public class OptimizedHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of item-priority pairs.
     */
    private final List<PriorityNode<T>> items;
    /**
     * {@link Map} of each item to its associated index in the {@code items} heap.
     */
    private final Map<T, Integer> itemToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        items = new ArrayList<>();
        itemToIndex = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        items.add(new PriorityNode<>(item, priority));
        itemToIndex.put(item, items.size()-1);
        swim(items.size()-1);
    }

    @Override
    public boolean contains(T item) {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // do something with the sink method
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void swim(int index) {
        if (index == 0) return;
        int parent = index / 2;
        double indexPriority = items.get(index).priority();
        double parentPriority = items.get(parent).priority();
        while (indexPriority < parentPriority) {
            swap(index, parent);
            swim(parent);
        }
    }

    public void sink(int index) {
        if (index == 0) return;
        double priority = items.get(index).priority();
        int rightChild =  index * 2 + 1;
        int leftChild = index * 2;
        int childIndex = leftChild;
        double childPriority = items.get(leftChild).priority();
        if (items.get(rightChild).item() == null && items.get(leftChild).item() == null) {
            return;
        }
        if (items.get(rightChild).item() != null) {
            if (items.get(rightChild).priority() < items.get(leftChild).priority()) {
                childPriority = items.get(leftChild).priority();
                childIndex = leftChild;
            } else {
                childPriority = items.get(rightChild).priority();
                childIndex = rightChild;
            }
        }
        if (childPriority < priority) {
            swap(index, childIndex);
            sink(childIndex);
        }
    }

    public void swap(int index1, int index2) {
        PriorityNode temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
        itemToIndex.put(items.get(index2).item(), index2);
        itemToIndex.put(items.get(index2).item(), index1);
    }
}
