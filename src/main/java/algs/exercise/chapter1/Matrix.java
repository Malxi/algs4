package algs.exercise.chapter1;

import edu.princeton.cs.algs4.StdOut;

public class Matrix{
    public static double dot(double[] x, double[] y) throws Exception{
        double ans = 0;
        if(x.length != y.length){
            throw new Exception("Vector length does not match: " + x.length + " and " + y.length);
        }
        for(int i = 0; i < x.length; i++){
            ans += x[i] * y[i];
        }
        return ans;
    }

    public static double[][] mult(double[][] a, double[][] b) throws Exception{
        int m, d1, d2, n;
        m = a.length;
        d1 = a[0].length;
        d2 = b.length;
        n = b[0].length;
        if(d1 != d2){
            throw new Exception("Matrix size dose not match: " + m + " x " + d1 + " and " + d2 + " x " + n);
        }
        
        double[][] ans = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < d1; k++) ans[i][j] += a[i][k] * b[k][j];
            }
        }
        return ans;

    }

    public static double[][] transpose(double[][] a){
        int m = a.length;
        int n = a[0].length;
        double[][] ans = new double[n][m];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans[j][i] = a[i][j];
            }
        }

        return ans;
    }

    public static void printMarix(double[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[0].length; j++){
                StdOut.printf( j == a[0].length - 1 ? "%-5.2f\n" : "%-5.2f ", a[i][j]);
            }
        }
    }

    public static void printVectot(double [] v){
        for(int j = 0; j < v.length; j++){
            StdOut.printf( j == v.length - 1 ? "%4.2f\n" : "%4.2f ", v[j]);
        }
    }

    public static void main(String[] args) {
        double[] vec_a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] vec_b = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        double[][] mat_a = new double[10][10];
        double[][] mat_b = new double[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                mat_a[i][j] = i * j + 1;
                mat_b[i][j] = 2 * i * j + 1; 
            }
        }
        try{
            //printVectot(vec_a);
            //printVectot(vec_b);
            //StdOut.println(dot(vec_a, vec_b));
            printMarix(mat_a);
            StdOut.println();
            //printMarix(mat_b);
            //printMarix(mult(mat_a, mat_b));
            printMarix(transpose(mat_a));
            //printMarix(transpose(mat_b));
        }catch(Exception e){
            StdOut.println(e.getMessage());
        }

    }
}