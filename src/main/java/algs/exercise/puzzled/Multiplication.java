package algs.exercise.puzzled;

public class Multiplication {
    
    public static int peasant(int x, int y) {
        int prod = 0;
        while (x != 0) {
            if ((x&1) == 1) {
                prod = prod + y;
            }
            x = x >>> 1;
            y = y + y;
        }
        return prod;
    }

    public static void main(String[] args) {
        System.out.println(peasant(2, 10));
        System.out.println(peasant(-3, -10));
    }
}