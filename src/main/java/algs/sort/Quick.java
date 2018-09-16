package algs.sort;

import algs.collection.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    
    private static void shuffle(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i + StdRandom.uniform(a.length - i);
            exch(a, i, j);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */
        shuffle(a);
        // sort(a, 0, a.length - 1);
        // sortNonrecursive(a);
        sortWithBentleyMcIlroyPartitioning(a, 0, a.length - 1);
    }

    private static void sortWithBentleyMcIlroyPartitioning(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

            // pointers cross
            if (i == j && eq(a[i], v)) exch(a, ++p, i);
            if (i >= j) break;
            
            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
        }

        i = j + 1;
        for (int k = lo; k <= p; k++) {
            exch(a, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            exch(a, k, i++);
        }
        sortWithBentleyMcIlroyPartitioning(a, lo, j);
        sortWithBentleyMcIlroyPartitioning(a, i, hi);
    } 

    private static void sortNonrecursive(Comparable[] a) {
        int lo = 0, hi = a.length - 1;
        int p;
        Stack<Integer> s = new Stack<Integer>();
        s.push(lo);
        s.push(hi);
        while(!s.isEmpty()) {
            hi = s.pop();
            lo = s.pop();
            if (lo < hi){
                p = partition(a, lo, hi);
                s.push(lo);
                s.push(p-1);
                s.push(p+1);
                s.push(hi);
            }
        }
    }

    private static void sortDuplicate(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, lo, hi);
        sort(a, lo, p-1);
        sort(a, p+1, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
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
        Integer[] a = {1,2,3,2,2,2,21,1,1,2, 3, 4, 1,1,6,4,654,756,7865};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}