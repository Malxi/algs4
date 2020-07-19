package algs.exercise.puzzled;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;
        long k;
        Scanner sc = new Scanner(System.in);
        n = 100;//sc.nextInt();
        m = 100;//sc.nextInt();
        k = 1000000000;//sc.nextInt();
        

        double[][] dp = new double[n+1][m+1];
        for (int i = 1; i <= n; i++) dp[i][0] = 1;
        for (int i = 1; i <= m; i++) dp[0][i] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        if (dp[n][m] < k) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        int len = m + n;
        for (int i = 0; i < len; i++) {
            if (n == 0) {
                sb.append('z');
                m--;
            }else if (m == 0) {
                sb.append('a');
                n--;
            }else {
                double l = dp[n-1][m];
                if (k <= dp[n-1][m]) {
                    sb.append('a');
                    n--;
                }else {
                    sb.append('z');
                    m--;
                    k -= dp[n-1][m];
                }
            }
        }
        System.out.println(sb.toString());
    }
}