package deque;



import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;
        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }
//the first item (if exists) is at sentinel.next
    private Node sentinel;
    private int size;
//creates an empty LinkedListDeque
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public LinkedListDeque(T i) {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.addFirst(i);
    }

    public int size() {
        return this.size;
    }

    public void addFirst(T x) {
        size++;
        Node newNode = new Node(x, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    public void addLast(T x) {
        size++;
        Node newNode = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            size--;
            return removeFirstHelper();
        } else {
            return null;
        }
    }
    private T removeFirstHelper() {
        Node newNext = sentinel.next.next;
        Node removeNode = sentinel.next;
        sentinel.next = newNext;
        newNext.prev = sentinel;
        return removeNode.item;
    }

    public T removeLast() {
        if (sentinel.prev != sentinel) {
            size--;
            return removeLastHelper();
        } else {
            return null;
        }
    }
    private T removeLastHelper() {
        Node newLast = sentinel.prev.prev;
        Node removeNode = sentinel.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        return removeNode.item;
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public T getLast() {
        Node back = getLastNode();
        return back.item;
    }
    private Node getLastNode() {
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public T get(int index) {
        if (index >= this.size() || index < 0) {
            return null;
        } else {
            Node currNode = sentinel;
            while (index >= 0) {
                currNode = currNode.next;
                index--;
            }
            return currNode.item;
        }
    }

    public T getRecursive(int index) {
        if (index >= this.size() || index < 0) {
            return null;
        }
        return getHelper(index, sentinel.next);
    }
    private T getHelper(int i, Node p) {
        if (i == 0) {
            return p.item;
        }
        return getHelper(i - 1, p.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        Node currNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return !isEmpty() && currNode != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            } else {
                T item = currNode.item;
                currNode = currNode.next;
                return item;
            }

        }
    }

    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
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

//    public static <TT> @NotNull LinkedListDeque<TT> of (TT @NotNull ... items) {
//        LinkedListDeque<TT> LLD = new LinkedListDeque<>();
//        for (TT item : items) {
//            LLD.addLast(item);
//        }
//        return LLD;
//    }

//    private static void main(String[] args) {
//        int n = 99;
//        LinkedListDeque<Integer> LLD1 = new LinkedListDeque<>();
//        for (int i = 0; i <= n; i++) {
//            LLD1.addLast(i);
//        }
//        LinkedListDeque<Integer> LLD2 = new LinkedListDeque<>();
//        for (int j = n; j >=0; j--) {
//            LLD2.addFirst(j);
//        }
//        LLD1.printDeque();
//        LLD2.printDeque();
//        System.out.println(LLD1.equals(LLD2));
//    }
}
