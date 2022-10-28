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
        items.add(null);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        PriorityNode node = new PriorityNode<>(item, priority);
        items.add(node);
        itemToIndex.put(item, items.size() - 1);
        swim(items.size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items.get(1).item();
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        T min = peekMin();
        swap(1, size());
        items.remove(items.size() - 1);
        itemToIndex.remove(min);
        sink(1);
        return min;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        int index = itemToIndex.get(item);
        swap(1, index);
        removeMin();
        swim(index);
        add(item, priority);
    }

    @Override
    public int size() {
        return items.size() - 1;
    }

    private boolean accessible(int index) {
        return 1 <= index && index <= size();
    }

    private static int parent(int index) {
        return index / 2;
    }

    private static int left(int index) {
        return index * 2;
    }

    private static int right(int index) {
        return left(index) + 1;
    }

    private int min(int index1, int index2) {
        if (!accessible(index1) && !accessible(index2)) {
            return 0;
        } else if (accessible(index1) && (!accessible(index2)
                || (items.get(index1).compareTo(items.get(index2))) < 0)) {
            return index1;
        } else {
            return index2;
        }
    }

    private void swim(int index) {
        int parent = parent(index);
        while (accessible(parent) && items.get(index).compareTo(items.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = parent(index);
        }
    }

    private void sink(int index) {
        int child = min(left(index), right(index));
        while (accessible(child) && items.get(index).compareTo(items.get(child)) > 0) {
            swap(index, child);
            index = child;
            child = min(left(index), right(index));
        }
    }

    private void swap(int index1, int index2) {
        PriorityNode<T> temp = items.get(index1);
        itemToIndex.put(items.get(index2).item(), index1);
        itemToIndex.put(temp.item(), index2);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
    }
}
