package algs.exercise.chapter1;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci
{
    private static long[] FibBuf = new long[100];

    public static long FibRe(int N)
    {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return FibRe(N-1) + FibRe(N-2);
    }

    public static long FibReSave(int N){
        if(N == 0) return 0;
        if(N == 1) return 1;
        if(FibBuf[N] != 0) return FibBuf[N];
        FibBuf[N] = FibReSave(N-1) + FibReSave(N-2);
        return FibBuf[N];
    }

    public static void main(String[] args)
    {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + FibReSave(N));
    }
}