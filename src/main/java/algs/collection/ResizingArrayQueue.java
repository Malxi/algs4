package algs.collection;

import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item>{
    private int first;
    private int last;
    private int N;
    private Item[] items;

    public ResizingArrayQueue(){
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void resize(int max){
        Item[] temp = (Item[] ) new Object[max];
        for(int i = 0; i < N; i++){
            temp[i % max] = items[(i + first) % items.length];
        }
        first = 0;
        last = (first + N) % max;
        items = temp;
    }

    public void enqueue(Item item){
        if(N == items.length){
            resize(2 * items.length);
        }
        items[last] = item;
        last = (last + 1) % items.length;
        N++;    
    }

    public Item dequeue(){
        Item item = items[first];
        items[first] = null;
        N--;
        first = (first + 1) % items.length;
        if(N > 0 && N == items.length / 4){
            resize(items.length / 2);
        }
        return item;
    }

    public Iterator<Item> iterator(){ 
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<Item>{
        private int i = first;
        private int n = N;
        public boolean hasNext() { return n > 0; }
        public Item next() { 
            Item item =  items[i];
            i = (i + 1) % items.length;
            n--;
            return item;
        }
        public void remove() { }
    }

    public static void main(String[] args) {
        String in = "to be or not to - be - - that - - - is";
        String[] strs = in.split("\\s+");
        ResizingArrayQueue<String> st = new ResizingArrayQueue<String>();
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
        for(String s: st){
            System.out.println(s);
        }
    }

}