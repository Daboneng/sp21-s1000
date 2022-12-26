package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public MaxArrayDeque(Comparator<T> c, T item) {
        this(c);
        this.addFirst(item);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (T i : this) {
            if (c.compare(max, i) < 0) {
                max = i;
            }
        }
        return max;
    }

    public T max() {
        return max(comparator);
    }
}
