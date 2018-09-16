package algs.exercise.puzzled;

import java.util.Scanner;

public class BagProblem {
    public static void main(String[] args) {
        int v, n;
        int[] a, b, dp;
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        n = sc.nextInt();
        a = new int[n];
        b = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        dp = new int[v + 1];
        dp[0] = 0;

        for (int i = 1; i <= v; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (i - a[j] >= 0 && dp[i-a[j]] + b[j] > max) max = dp[i-a[j]] + b[j];
            }
            dp[i] = max;
        }

        System.out.println(dp[v]);
    }
}