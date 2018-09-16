package algs.sort;

import edu.princeton.cs.algs4.StdOut;

public class Heap {
    private static void sink(Comparable[] a, int k, int N) {
        while (2*k+1 <= N) {
            int j = 2*k+1;
            if (j < N && less(a[j], a[j+1])) j = j + 1;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int k = (N - 1)/2; k>= 0; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 0, N--);
            sink(a, 0, N);
        }
    }

    public static void main(String[] args) { 
        // Read strings from standard input, sort them, and print.
        Integer[] a = {1,2,3,2,2,2,21,1,1,2, 3, 4, 1,1,6,4,654,756,7865};
        sort(a);
        assert isSorted(a);
        show(a);
    }
    
}