package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 1;
        nextLast = 2;
    }
//    public ArrayDeque(T item) {
//        this();
//        this.addFirst(item);
//    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private float useRatio() {
        return (float) size / items.length;
    }

    private int getFirstIndex() {
        return Math.floorMod(nextFirst + 1, items.length);
    }

    private int getLastIndex() {
        return Math.floorMod(nextLast - 1, items.length);
    }

    private void resize() {
        if (isFull()) {
            resizeHelper(items.length * 2);
        } else if (useRatio() < 0.25 && items.length >= 16) {
            resizeHelper(items.length / 2);
        }
    }

    private void resizeHelper(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (getFirstIndex() < getLastIndex()) {
            System.arraycopy(items, getFirstIndex(), a, 2, size);
        } else {
            int startPos = items.length - getFirstIndex();
            System.arraycopy(items, getFirstIndex(), a, 2, startPos);
            System.arraycopy(items, 0, a, 2 + startPos, getLastIndex() + 1);

        }
        items = a;
        nextFirst = 1;
        nextLast = Math.floorMod((getFirstIndex() + size), items.length);
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize();
        }
        items[nextFirst] = item;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize();
        }
        items[nextLast] = item;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
    }

    private T remove(int index) {
        T remove = items[index];
        size--;
        items[index] = null;
        return remove;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T remove = remove(getFirstIndex());
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        resize();
        return remove;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T remove = remove(getLastIndex());
        nextLast = Math.floorMod(nextLast - 1, items.length);
        resize();
        return remove;
    }



    public T get(int index) {
        int circularIndex = Math.floorMod(getFirstIndex() + index, items.length);
        return items[circularIndex];
    }

//    public T getFirst() {
//        int circularIndex = Math.floorMod(getFirstIndex(), items.length);
//        return items[circularIndex];
//    }
//
//    public T getLast() {
//        int circularIndex = Math.floorMod(getLastIndex(), items.length);
//        return items[circularIndex];
//    }



    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            } else {
                T item = get(currIndex);
                currIndex++;
                return item;
            }

        }
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.println(get(i));
                break;
            }
            System.out.print(get(i) + " ");
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque o1 = (Deque) o;
        if (this.size() != o1.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(this.get(i).equals(o1.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
