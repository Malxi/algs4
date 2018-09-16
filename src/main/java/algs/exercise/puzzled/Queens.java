package algs.exercise.puzzled;

import java.util.List;

public class Queens {

    private static int count = 1;

    private static boolean isConflict(int[] board, int current) {
        for (int i = 0; i < current; i++) {
            if (board[i] == board[current]) {
                return true;
            }
            if (current - i == Math.abs(board[current] - board[i])) {
                return true;
            }
        }
        return false;
    }

    private static void solve(int[] board, int n, int current) {
        if (current == n) {
            System.out.print(count++ + ": ");
            for (int i = 0; i < n; i++) {
                System.out.printf(i == n - 1 ? "%d\n" : "%d ", board[i]);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            board[current] = i;
            if (!isConflict(board, current)) {
                solve(board, n, current + 1);
            }
        }
    }

    public static void solve(int n) {
        int[] board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
        solve(board, n, 0);
    }

    public static void main(String[] args) {
        solve(1);
    }
}