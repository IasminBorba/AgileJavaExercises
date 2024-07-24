package clock;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ring<T> implements Iterable<T> {
    private Node<T> current;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;

        Node(T element) {
            this.element = element;
        }
    }

    public void add(T element){
        Node<T> node = new Node<>(element);
        if(current == null) {
            node.next = node;
            node.previous = node;
            current = node;
        } else {
            node.next = current.next;
            node.previous = current;
            current.next = node;
        }
    }
    public boolean hasElement(T element){
        if(current != null) {
            Iterator<T> it = iterator();
            while (it.hasNext()) {
//                System.out.println(next);
                if (it.next() == element)
                    return true;
            }
        }
        return false;
    }

    public int size() {
        if (current == null) {
            return 0;
        }

        int countElements = 0;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                countElements++;
            }
        }
        return countElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    private class RingIterator implements Iterator<T> {
        private final Node<T> start = current;
        private Node<T> pointer = current;
        private boolean firstIteration = true;
        @Override
        public boolean hasNext() {
            return firstIteration || pointer != start;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = pointer.element;
            pointer = pointer.next;
            firstIteration = false;
            return data;
        }
    }
}
