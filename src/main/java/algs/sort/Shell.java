package algs.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Shell{
    
    public static void sort(Comparable[] a) {
        /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1){
            for(int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h){
                    exch(a, j, j-h);
                }
            }
            h = h / 3;
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

    public static void main(String[] args) { 
        // Read strings from standard input, sort them, and print.
        Integer[] a = {1, 2,3,2,2,2,21,21,21,3,12,4,6,4,654,756,7865};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}