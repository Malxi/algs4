package algs.exercise.assignment.queue;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        k = 3;
        String s = StdIn.readString();
        Deque dq = new Deque<String>();
        while(s != null) {
            if (dq.size() == k)
            dq.addLast(s);
        }
    }
 }