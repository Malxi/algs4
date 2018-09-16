package algs.exercise.assignment.queue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    Node<Item> first;
    Node<Item> last;
    int n;

    /**
     * construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * helper linked list class
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> left;
        private Node<Item> right;
    }

    /**
     * is the deque empty?
     * @return true if this Deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * return the number of items on the deque
     * @return size of Deque
     */
    public int size() {
        return n;
    }

    /**
     * add the item to the front
     * @param  Item the item add to the front
     * @throws java.lang.IllegalArgumentException if the client calls with a null argument.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.left = null;
        if (oldFirst == null) {
            last = first;
            first.right = null;
        }else {
            first.right = oldFirst;
            oldFirst.left = first;
        }
        n++;
    }

    /**
     * add the item to the end
     * @param Item the item to the end
     * @throws java.lang.IllegalArgumentException if the client calls with a null argument.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.right = null;
        if(oldLast == null) {
            first = last;
            last.left = null;
        }else {
            last.left = oldLast;
            oldLast.right = last;
        }
        n++;
    }

    /**
     * remove and return the item from the front
     * @return the item at the from of Deque
     * @throws java.util.NoSuchElementException if the client calls when the deque is empty.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.right;
        if (first != null) first.left = null;
        n--;
        return item;
    }

    /**
     * remove and return the item from the end
     * @return the item at the end of Deque
     * @throws java.util.NoSuchElementException if the client calls when the deque is empty.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.left;
        if (last != null) last.right = null;
        n--;
        return item;
    }

    /**
     * return an iterator over items in order from front to end
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    /**
     * an iterator, doesn't implement remove() since it's optional
     */
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current == null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new  java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.right;
            return item;
        }

    }


    /**
     * unit testing (optional)
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        
    }

}