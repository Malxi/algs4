package algs.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Merge {

    private static final int CUTOFF = 7;
    private static final int M = 4;

    public static void sort(Comparable[] a) {
        /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */
        Comparable[] aux = a.clone();
        // sort(aux, a, 0, a.length - 1);
        // sortBU(a, aux);
        blocksMergeSort(a);
    }

    private static void blocksMergeSort(Comparable[] a) {
        int bn = a.length / M;
        Comparable[] aux = new Comparable[M];
        /* slection sort for every blocks */
        for(int i = 0; i < bn; i++) {
            blocksSort(a, aux, i*M, Math.min(i*M+M-1, a.length-1));
            // selectionSort(a, i*bn, Math.max(i*bn+bn-1, a.length-1));
        }

        /* sort blocks */
        for (int i = 0; i < bn; i++) {
            int min = i;
            for (int j = i+1; i < bn; i++) {
                if (less(a[j*M], a[i*M])) min = j;
            }
            if (i < min)
                for (int k = 0; k < Math.min(M, a.length-min*M+1); k++){
                    exch(a, i*M+k, min*M+k);
                }
        }

        /* merge every blocks */
        for(int i = 0; i < bn; i++) {
            blockMerge(a, aux, i*M, i*M+M-1, Math.min(i*M+M+M-1, a.length-1));
        }
    }

    private static void blocksSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(lo + CUTOFF >= hi){
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        blocksSort(a, aux, lo, mid);
        blocksSort(a, aux, mid+1, hi);
        if (less(a[mid], a[mid+1])) {
            blockMerge(a, aux, lo, mid, hi);
        }
    }

    private static void blockMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int p = 0;
        for (int i = lo; i <= mid; i++) {
            aux[p++] = a[i];
        }
        int i = 0, j = mid + 1;
        p = p -1;
        for (int k = lo; k <= hi; k++) {
            if (i > p) a[k] = a[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], a[j])) a[k] = aux[i++];
            else a[k] = a[j++];
        }
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if(lo + CUTOFF >= hi){
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
        }

        merge(src, dst, lo, mid, hi);
    }

    private static void sortBU(Comparable[] a, Comparable[] aux) {
        int N = a.length;
        for (int sz = 1; sz < N; sz += sz) {
            /* merge [lo, lo+sz-1, lo+sz+sz-1], lo increment by step of 2*sz */
            for(int lo = 0; lo < N - sz; lo += sz+sz) {
                fasterMerge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    private static void fasterMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }

        for (int j = mid + 1; j <= hi; j++) {
            aux[j] = a[hi-j+mid+1];
        }
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
        }
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(src[i], src[j])) dst[k] = src[i++];
            else dst[k] = src[j++];
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

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static void selectionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
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
        Integer[] a = {1,2,3,2,2,2,21,21,21,3,12,4,6,4,654,756,7865};
        // insertionSort(a, 0, a.length - 1);
        // show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}