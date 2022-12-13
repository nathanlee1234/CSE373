package deques;

import java.util.ArrayList;

public abstract class ArrayListDeque<T> implements Deque<T> {

    private final ArrayList<T> data;

    public ArrayListDeque() {
        data = new ArrayList<>();
    }

    public void addFirst(T item) {
        data.add(0, item);
    }
    public void addLast(T item) {
        data.add(item);
    }

    public T removeFirst() {
        if (data.size() == 0) return null;
        return data.remove(0);
    }

    public T removeLast() {
        if (data.size() == 0) return null;
        return data.remove(data.size() - 1);
    }

    public T get(int index) {
        if (index >= data.size() || index < 0) return null;
        return data.get(index);
    }

    public int size() {
        return data.size();
    }
}
