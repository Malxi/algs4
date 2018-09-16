package algs.exercise.chapter2;

import algs.collection.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class CubeSum implements Comparable<CubeSum>{
    private int sum;
    private int i;
    private int j;

    public CubeSum(int i, int j) {
        this.sum = i*i*i + j*j*j;
        this.i = i;
        this.j = j;
    }

    public int compareTo(CubeSum that) {
        if (this.sum < that.sum) return -1;
        if (this.sum > that.sum) return 1;
        return 0;
    }

    public String toString() {
        return sum + " + " + i + "^3" + "+" + j + "^3";
    }

    public static void main(String[] args) {
        int n = 12;
        MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
        for (int i = 0; i <= n; i++) {
            pq.insert(new CubeSum(i, i));
        }

        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            StdOut.println(s);
            if (s.j < n) {
                pq.insert(new CubeSum(s.i, s.j + 1));
            }

        }
    }
}