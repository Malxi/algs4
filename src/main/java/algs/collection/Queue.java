package algs.collection;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else{
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        N--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public void remove(){

        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        String in = "to be or not to - be - - that - - - is";
        String[] strs = in.split("\\s+");
        Queue<String> st = new Queue<String>();
        for(String item: strs){
            if(item.equals("-")){
                if(!st.isEmpty()){
                    System.out.print(st.dequeue());
                    System.out.println();
                }
            }else{
                st.enqueue(item);
            }
        }
        System.out.println(st.size() + " left on queue");
        for(String item: st){
            System.out.println(item);
        }
    }
}