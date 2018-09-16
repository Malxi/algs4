package algs.collection;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
    private Node first;
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

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek(){
        return first.item;
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
        Stack<String> st = new Stack<String>();
        for(String item: strs){
            if(item.equals("-")){
                if(!st.isEmpty()) System.out.println(st.pop());
            }else{
                st.push(item);
            }
        }
        System.out.println(st.size() + " left on queue");
        for(String item: st){
            System.out.println(item);
        }
    }
}