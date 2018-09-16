package algs.exercise.chapter2;

public class Ex2315 {

    public static void sort(int[] nuts, int[] bolts) {
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    private static void sort(int[] nuts, int[] bolts, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(nuts, bolts, lo, hi);
        sort(nuts, bolts, lo, p-1);
        sort(nuts, bolts, p+1, hi);
    }

    private static int partition(int[] nuts, int[] bolts, int lo, int hi) {
        int nv = nuts[lo];
        int i = lo - 1, j = hi + 1;
        while(true) {
            while (bolts[++i] < nv) if (i == hi) break;
            while (bolts[--j] > nv) if (j == lo) break;
            if (i >= j) break;
            int temp = bolts[i];
            bolts[i] = bolts[j];
            bolts[j] = temp;
        }
        int bv = bolts[j];
        i = lo - 1;
        j = hi + 1;
        while(true) {
            while (nuts[++i] < bv) if (i == hi) break;
            while (nuts[--j] > bv) if (j == lo) break;
            if (i >= j) break;
            int temp = nuts[i];
            nuts[i] = nuts[j];
            nuts[j] = temp;
        }
        return j;
    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(i == a.length - 1 ? "%d\n" : "%d ", a[i]);
        }
    }

    public static void main(String[] args) {
        int[] nuts = {1,3,2,4,6,5,7,9,8,0};
        int[] bolts = {7,4,1,8,5,2,9,6,3,0};
        sort(nuts,bolts);
        show(nuts);
        show(bolts);
    }
}