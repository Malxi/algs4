package algs.sort;

import java.util.Collection;

import edu.princeton.cs.algs4.StdOut;

public class Radix {

    public static void sort(Comparable[] a) {

    }

    public static void lsd(String[] a) {
        int N = a.length;
        int W = a[0].length();
        int R = 257;
        String[] temp = new String[a.length];
        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d)+1]++;
            }
            for (int i = 1; i < R; i++) {
                count[i] += count[i-1];
            }
            for (int i = 0; i < N; i++) {
                temp[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = temp[i];
            }
        }
    }

    private static void show(Comparable[] a) {
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static void main(String[] args) { 
        // Read strings from standard input, sort them, and print.
        String[] a = {"1234", "2345", "4567", "1111", "2222"};
        lsd(a);
        show(a);
    }
}