package algs.exercise.chapter1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

import algs.collection.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Test{
    static double[][] bino = new double[200][200];
    static int offset = 100;

    public static double sqrt(double num){
        /* x_{n+1} = (x_{n} + \frac{1}{x_{n}}) / 2 */
        double t = num;
        while (Math.abs(t - num/t) > .001)
            t = (num/t + t) / 2.0;
        return t;
    }

    public static int sum_n(int n){
        int sum = 0;
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                sum++;
        return sum;
    }

    public static int lg(int N){
        int rt = 0;
        for(int i = 2; i <= N; i *= 2){
            rt++;
        }
        return rt;
    }

    public static int[] histogram(int []arr, int M){
        int[] his = new int[M];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= 0 && arr[i] < M) his[arr[i]] += 1;
        }
        return his;
    }

    public static String exR1(int n)
    {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }

    public static double binomial(int N, int k, double p)
    {
        if ((N == 0) || (k < 0)) return 1.0;
        if(bino[N-1][k+offset] == 0.0) bino[N-1][k+offset] = binomial(N-1, k, p);
        if(bino[N-1][k+offset-1] == 0.0) bino[N-1][k+offset-1] = binomial(N-1, k-1, p);
        return (1.0 - p)*bino[N-1][k+offset] + p*bino[N-1][k+offset-1];
    }

    public static void shuffle(double[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int r = i + StdRandom.uniform(N - i);
            //int r = StdRandom.uniform(N);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void displayCircle(int N, double p) throws InterruptedException{
        double[] xs = new double[N];
        double[] ys = new double[N];
        for(int i = 0; i < N; i++){
            double theta = (double)i / N * 2 * Math.PI;
            double x = 0.4 * Math.sin(theta) + 0.5;
            double y = 0.4 * Math.cos(theta) + 0.5;
            xs[i] = x;
            ys[i] = y;
            StdDraw.filledCircle(x, y, 0.01);
            Thread.sleep(100);
        }
        for(int i = 0; i < N - 1; i++){
            for(int j = i + 1; j < N; j++){
                if(StdRandom.random() >= p)StdDraw.line(xs[i], ys[i], xs[j], ys[j]);
                Thread.sleep(10);
            }
        }
    }

    public static void testcaseMatrix(){
        //System.out.print((0 + 15) / 2);
        //StdOut.print(2.0e-6 * 1000000000000.1);
        //StdOut.println(binomial(100, 50, 0.2));
        int N = 10;
        double[] nums = new double[N];
        int[][] prob = new int[N][N];
        for(int i = 0; i < N; i++) nums[i] = i;
        for(int i = 0; i < N; i++){
            shuffle(nums);
            for(int j = 0; j < N; j++){
                int num = (int)nums[j];
                prob[j][num]++;
            }
            Matrix.printVectot(nums);
        }
        StdOut.println("------Result-------");
        for(int i = 0; i < N; i++){
            StdOut.print(i + (i == N - 1 ? "\n" : " "));
        }
        for(int i = 0; i < N; i++){
            int sum = 0;
            for(int j = 0; j < N; j++){
                sum += prob[i][j];
            }
            for(int j = 0; j < N; j++){
                StdOut.print((double)prob[i][j] / sum + (j == N - 1 ? "\n" : " "));
            }
        }
    }

    public static void interval2DIntersect(int N, double min, double max){
        double[] x1 = new double[N];
        double[] y1 = new double[N];
        double[] x2 = new double[N];
        double[] y2 = new double[N];

        for(int i = 0; i < N; i++){
            x1[i] = StdRandom.uniform(min, max);
            y1[i] = StdRandom.uniform(min, max);
            x2[i] = StdRandom.uniform(min, max);
            y2[i] = StdRandom.uniform(min, max);
        }
        
        StdDraw.setScale(0, max + 3);
        StdDraw.setPenColor(StdDraw.GREEN);
        for(int i = 0; i < max + 3; i++){
            StdDraw.line(i, 0, i, max + 3);
            StdDraw.line(0, i, max + 3, i);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int i = 0; i < N; i++){
            for(int j =  i + 1; j < N; j++){
                double halfwidth, halfhight;
                double[] intervalX = new double[4];
                intervalX[0] = Math.min(x1[i], x2[i]);
                intervalX[1] = Math.max(x1[i], x2[i]);
                intervalX[2] = Math.min(x1[j], x2[j]);
                intervalX[3] = Math.max(x1[j], x2[j]);
                double[] intervalY = new double[4];
                intervalY[0] = Math.min(y1[i], y2[i]);
                intervalY[1] = Math.max(y1[i], y2[i]);
                intervalY[2] = Math.min(y1[j], y2[j]);
                intervalY[3] = Math.max(y1[j], y2[j]);
                
                boolean intersectX = Math.max(intervalX[0], intervalX[2]) < Math.min(intervalX[1], intervalX[3]);
                boolean intersectY = Math.max(intervalY[0], intervalY[2]) < Math.min(intervalY[1], intervalY[3]);
                
                if(intersectX && intersectY){
                    StdDraw.filledCircle(intervalX[0], intervalY[0], 0.1);
                    halfwidth = (intervalX[1] - intervalX[0]) / 2;
                    halfhight = (intervalY[1] - intervalY[0]) / 2;
                    StdDraw.rectangle(intervalX[0] + halfwidth, intervalY[0] + halfhight, halfwidth, halfhight);
                    StdDraw.filledCircle(intervalX[2], intervalY[2], 0.1);
                    halfwidth = (intervalX[3] - intervalX[2]) / 2;
                    halfhight = (intervalY[3] - intervalY[2]) / 2;
                    StdDraw.rectangle(intervalX[2] + halfwidth , intervalY[2] + halfhight, halfwidth, halfhight);
                    try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    double interx1 = Math.max(intervalX[0], intervalX[2]);
                    double interx2 = Math.min(intervalX[1], intervalX[3]);
                    double intery1 = Math.max(intervalY[0], intervalY[2]);
                    double intery2 = Math.min(intervalY[1], intervalY[3]);
                    StdDraw.filledRectangle(interx1 + (interx2 - interx1) / 2, intery1 + (intery2 - intery1) / 2, (interx2 - interx1) / 2, (intery2 - intery1) / 2);
                }
                
            }
        }

    }

    public static Boolean isCircularShifts(String s, String t){
        if(s.length() != t.length()) return false;
        for(int i = 0; i < s.length(); i++){
            if(t.indexOf(s) > 0) return true;
            t = t + t.charAt(i);
        }
        return false;
    }

    public static String mystery(String s)
    {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return mystery(b) + mystery(a);
    }

    public static boolean parentheses(String str){
        Stack<Character> st = new Stack<Character>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            }else{
                if(st.isEmpty()) return false;
                char mch = st.pop();
                switch(ch){
                    case '}': if(mch != '{') return false;break;
                    case ')': if(mch != '(') return false;break;
                    case ']': if(mch != '[') return false;break;
                    default:
                }
            }
        }
        return true;
    }

    public static void permutation(int N, Stack st, int k){
        
    }

    public static boolean twoBinarySearch(int[][] nums, int target, int rlo, int clo, int rhi, int chi){
        //int rlo = -1, clo = -1, rhi = nums.length, chi = nums[0].length;
        if(rlo + 1 < rhi && clo + 1 < chi){
            int rmid = rlo + (rhi - rlo) / 2;
            int cmid = clo + (chi - clo) / 2;
            if(nums[rmid][cmid] <= target){
                rlo = rmid;
                clo = cmid;
                return twoBinarySearch(nums, target, rmid, cmid, rhi, chi);
            }
            else{
                boolean res;
                if(nums[rlo][cmid] <= target){
                    res = twoBinarySearch(nums, target, rlo, cmid, rmid, chi);
                    if(res) return true;
                }
                if(nums[rmid][clo] <= target){
                    res = twoBinarySearch(nums, target, rmid, clo, rhi, cmid);
                    if(res) return true;
                }
                res = twoBinarySearch(nums, target, rlo, clo, rmid, cmid);
                if(res) return true;
            }
        }

        if(rlo != -1 && clo != -1 && nums[rlo][clo] == target){
            return true;
        }

        return false;
    }

    private static int binarySearch(int[] array, int lo, int hi){
        if(lo >= hi) return 0;
        while(lo < hi && array[lo] >= array[hi]){
            int mid = lo + (hi - lo) / 2;
            if(array[mid] >= array[lo]){
                lo = mid + 1;
            }else if(array[mid] <= array[hi]){
                hi = mid - 1;
            }
        }
        return array[lo];
    }

    public static long permutation(int k){
        long ans = 1;
        for(int i = 1; i <= k; i++){
            ans *= i;
        }
        return ans;
     }
     
     public static int JumpFloor(int target) {
         int x, y;
         int sum = 0;
         for(x = 0; x <= target; x++){
             int rest = target - x;
             if(rest % 2 == 0){
                y = rest / 2;
                sum += permutation(x + y)/(permutation(x)*permutation(y));
             }
         }
         return sum;
     }

     public static double Power(double base, int exponent) {
        double ans = 1;
        while(exponent > 0){
            if((exponent & 1) == 1) ans *= base;
            base *= base;
            exponent = exponent >>> 1;
        }
        return ans;
  }

  public static int MoreThanHalfNum_Solution(int [] array) {
    if(array.length == 0) return 0;
    int cnt = 1;
    int v = array[0];
    for(int i = 1; i < array.length; i++){
        if(cnt == 0){
            v = array[i];
            cnt = 1;
        }if(array[i] == v){
            cnt++;
        }else {
            cnt--;
        }

    }
        return cnt > 0 ? v : 0;
    }   

    private static int partition(int[] input, int lo, int hi){
        int v = input[lo];
        int blank = lo;
        while(lo < hi){
            while(lo < hi && input[hi] >= v) hi--;
            if(lo < hi){
                input[blank] = input[hi];
                blank = hi;
            }
            while(lo < hi && input[lo] <= v) lo++;
            if(lo < hi){
                input[blank] = input[lo];
                blank = lo;
            }
        }
        input[blank] = v;
        return blank;
    }
    
    private static void halfSort(int[] input, int lo, int hi, int k){
        if(hi <= lo) return;
        int p = partition(input, lo, hi);
        if(k <= p){
            halfSort(input, lo, p - 1, k);
        }else{
            halfSort(input, lo, p - 1, k);
            halfSort(input, p + 1, hi, k);
        }
    }
    
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        halfSort(input, 0, input.length - 1, k);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            res.add(input[i]);
        }
        return res;
    }

    public static int NumberOf1Between1AndN_Solution(int n) {
        int[] pattern = new int[11];
        int len = 0;
        int sum = 0;
        while(n > 0){
            pattern[len++] = n % 10;
            n /= 10;
        }
        for(int i = 0; i < len; i++){
            int cnt = 1;
            if(pattern[i] >= 1){
                for(int j = i + 1; j < len; j++){
                    cnt *= pattern[j] + 1;
                }
                for(int j = 0; j < i; j++){
                    if(pattern[i] == 1)
                        cnt *= (pattern[j] == 0 ? 1: pattern[j]);
                    else 
                        cnt *= 9;
                }
            }
            sum += cnt;
        }
        return sum;
    }

    public static int GetNumberOfK(int [] array , int k) {
        int lo, hi;
        int s = 0, e = 0;
        lo = -1;
        hi = array.length;
        while(lo - hi < 1){
            int mid = lo + (hi - lo) / 2;
            if(array[mid] <= k){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        s = lo == -1 ? 0 : array[lo] == k ? lo : 0;
        
        lo = -1;
        hi = array.length;
        while(lo - hi < 1){
            int mid = lo + (hi - lo) / 2;
            if(array[mid] >= k){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        e = hi == array.length ? 0 : array[hi] == k ? hi : 0;
        
        return s - e + 1;
    }

    public static int GetUglyNumber_Solution(int index) {
        ArrayList<Integer> list = new  ArrayList<Integer>();
        int[] seed = new int[3];
        list.add(1);
        seed[0] = 0;
        seed[1] = 0;
        seed[2] = 0;

        while(list.size() <= index){
            int a = 2 * list.get(seed[0]);
            int b = 3 * list.get(seed[1]);
            int c = 5 * list.get(seed[2]);
            int t = Math.min(Math.min(a, b), c);
            list.add(t);
            if(a == t) seed[0] += 1;
            if(b == t) seed[1] += 1;
            if(c == t) seed[2] += 1;
        }
        
        return list.get(index - 1);
    }

    private static int cnt = 0;
    public static int[] partition2p(int[] nums, int lo, int hi){    
        int v = nums[lo], blank = lo;
        int i = lo, j = hi;

        while(i < j){
            while(i < j && nums[j] >= v) j--;
            if(i < j){
                nums[blank] = nums[j];
                cnt += j - blank;
                blank = j;
            }
            while(i < j && nums[i] <= v) i++;
            if(i < j){
                nums[blank] = nums[i];
                blank = i;
            }
        }
        nums[blank] = v;
        int[] bound = new int[2];
        bound[0] = blank - 1;
        bound[1] = blank + 1;
        return bound;
    }

    public static void sort(int[] nums, int lo, int hi){
        if(lo >= hi) return;
        int[] bound = partition2p(nums, lo, hi);
        int lp = bound[0];
        int rp = bound[1];
        sort(nums, lo, lp);
        sort(nums, rp, hi);
    }

    public static void testcase(){
        //String s = "(1+(4+5+2)-3)+(6+8)";
        //String s = "1 + 1";
        //String s = " (1 + 2) * 3";
        //String s = " 2-1 + 2 ";
        //String s = "   (  3 ) ";
        //StdOut.println(Expression.calculate(s));
        //String string1 = "[(])";
        //String string2 = "TGGCGAC";
        //StdOut.println(parentheses(string1));
        //StdOut.println(cp(25, 3, 5));
        int n = 0;
        int[] nums = {1,2,3, 4, 5, 6, 0};
        sort(nums, 0, nums.length - 1);
        System.out.println(cnt);
    }
}