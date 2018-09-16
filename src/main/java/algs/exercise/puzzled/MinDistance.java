package algs.exercise.puzzled;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MinDistance{

    private static class Point{
        public double x;
        public double y;

        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static double distance(Point a, Point b){
        double x = a.x - b.x;
        double y = a.y - b.y;
        return Math.sqrt(x*x + y*y);
    }

    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        while(N != 0){
            Point[] points = new Point[N];
            for(int i = 0; i < N; i++){
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                points[i] =  new Point(x, y);
            }
            System.out.printf("%.2f", radius(points) / 2);
            System.out.println();;
            N = sc.nextInt();
        }
        sc.close();
    }

    private static double radius(Point[] points){
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                return a.x > b.x ? 1 : -1;
            }
        });
        return radius(points, 0, points.length - 1);
    }

    private static double radius(Point[] points, int lo, int hi){
        if(hi - lo == 1) return distance(points[hi], points[lo]);
        else if (hi - lo < 1) return Double.MAX_VALUE;
        else{
            int mid = lo + (hi - lo) / 2;
            double deltaLeft = radius(points, lo, mid);
            double deltaRigth = radius(points, mid + 1, hi);
            double deltaMin = Math.min(deltaLeft, deltaRigth);
            
            int PLPos, PRPos;
            int PLlen = 0, PRLen = 0;
            for(PLPos = mid; PLPos >= lo; PLPos--){
                if(points[PLPos].x - points[mid].x > deltaMin){
                    break;
                }
                PLlen++;
            }
            for(PRPos = mid + 1; PRPos <= hi; PRPos++){
                if(points[PRPos].x - points[mid].x > deltaMin){
                    break;
                }
                PRLen++;
            }

            Point[] PL = new Point[PLlen];
            for(int i = 0; i < PLlen; i++){
                PL[i] = points[i + PLPos + 1];
            }
            Arrays.sort(PL, new Comparator<Point>() {
                @Override
                public int compare(Point a, Point b) {
                    return a.y > b.y ? 1 : -1;
                }
            });

            Point[] PR = new Point[PRLen];
            for(int i = 0; i < PRLen; i++){
                PR[i] = points[i + mid + 1];
            }
            Arrays.sort(PR, new Comparator<Point>() {
                @Override
                public int compare(Point a, Point b) {
                    return a.y > b.y ? 1 : -1;
                }
            });

            double min = Double.MAX_VALUE;
            int lb = 0;
            for(Point p : PL){
                while(lb < PR.length && PR[lb].y < p.y - deltaMin) lb++;
                if(lb == PR.length) break;
                for(int i = 0; i < 6 && lb + i < PR.length ; i++){
                    min = Math.min(distance(p, PR[lb + i]), min);
                }
            }

            return Math.min(deltaMin, min);
        }
    }
}