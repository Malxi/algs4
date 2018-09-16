package algs.collection;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max+1];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j = j + 1;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public MinPQ(int max) {
        /* create a priority queue of initial capacity max */
        pq = (Key[]) new Comparable[max+1];
    }

    public MinPQ(){
        /* create a priority queue of initial capacity max */
        pq = (Key[]) new Comparable[1+1];
    }

    public MinPQ(Key[] a) {
        /* create a priority queue from the keys in a[] */
    }

    public void insert(Key v) {
        /* insert a key into priority queue */
        if (N + 1 == pq.length) {
            resize(pq.length * 2);
        }
        pq[++N] = v;
        swim(N);
    }

    public Key min() {
        /* return the largest key */
        return pq[1];
    }

    public Key delMin() {
        /* return and remove the largest key */
        Key min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if (N > 0 && N == pq.length / 4) {
            resize(pq.length / 2);
        }
        return min;
    }

    public boolean isEmpty() {
        /* is the priority queue empty? */
        return N == 0;
    }

    public int size() {
        /* number of keys in the priority queue */
        return N;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> heap = new MaxPQ<Integer>();
        int[] a = {10, 2, 7, 9, 5, 3};
        for (int i : a)
            heap.insert(i);
        while(!heap.isEmpty()) {
            System.out.println(heap.delMax());
        }
    }
}