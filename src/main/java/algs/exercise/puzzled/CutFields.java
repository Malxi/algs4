package algs.exercise.puzzled;
import java.util.*;

public class CutFields {
    public static void main(String[] args) {
        int m, n;
        int[][] field;
        Scanner sc = new Scanner(System.in);
        
        String[] temp = sc.nextLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        field = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            String inl = sc.nextLine();
            for (int j = 0; j < m; j++) {
                field[i][j] = inl.charAt(j) - '0';
            }
        }
        
        int[][] sum = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] += sum[i][j-1] + sum[i-1][j] + field[i-1][j-1];
            }
        }
        
        int lo = 0, hi = sum[n][m];
        int ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(sum, mid)) {
                lo = mid + 1;
                ans = mid;
            }else {
                hi = mid - 1;
            }
        }
        System.out.println(ans);
    }
    
    private static boolean check(int[][] sum, int k) {
        int n = sum.length - 1;
        int m = sum[0].length - 1;
        for (int p1 = 1; p1 < m - 2; p1++) {
            for (int p2 = p1 + 1; p2 < m - 1; p2++) {
                for (int p3 = p2 + 1; p3 < m; p3++) {
                    int cnt = 0, ly = 0;
                    for (int y = 1; y <= n; y++) {
                        if (calc(sum, 0, ly, p1, y) >= k &&
                            calc(sum, p1, ly, p2, y)>= k &&
                            calc(sum, p2, ly, p3, y)>= k &&
                            calc(sum, p3, ly, m, y)>= k) {
                            ly = y;
                            cnt++;
                        }
                    }
                    if (cnt == 4) return true;
                }
            }
        }
        return false;
    }
    
    private static int calc(int[][] sum, int x1, int y1, int x2, int y2) {
        
        int s =  sum[x2][y2] - sum[x1][y2] - sum[x2][y1] + sum[x1][y1];
        return s;
    }
}