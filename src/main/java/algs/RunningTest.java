package algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

import javax.print.attribute.standard.MediaSize.Other;

import algs.collection.BST;
import algs.collection.RedBlackBST;
import algs.exercise.puzzled.CutFields;
import algs.exercise.puzzled.LCS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.AVLTreeST;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RunningTest {
    
    private static Integer[] genRandomArray(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(N);
        }
        return a;
    }

    private static void quickSortTest() {

    }

    private static void symbolTableTestClient() {
        String data = "S E A R C H E X A M P L E";
        String[] inp = data.split("\\s+");
        BST<String, Integer> st = new BST<String, Integer>();

        for (int i = 0; i < inp.length; i++) {
            st.put(inp[i], i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        
    }

    private static void frequencyCounter(String path, int minlen) {
        In in = new In(path);      // input file
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        AVLTreeST<String, Integer> tst = new AVLTreeST<String, Integer>();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minlen) continue;

            if (st.contains(word) != tst.contains(word)) {
                StdOut.println("key: " + word);
                break;
            }

            if (st.contains(word)) {
                int sv = tst.get(word);
                int v = st.get(word);
                if (sv != v) {
                    StdOut.println("std: " + word + " " + tst.get(word));
                    StdOut.println("res: " + word + " " + st.get(word));
                    break;
                }
            }

            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);

            if (!tst.contains(word)) tst.put(word, 1);
            else tst.put(word, tst.get(word) + 1);
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        String tmax = "";
        tst.put(tmax, 0);
        for (String word : tst.keys()) {
            if (tst.get(word) > tst.get(max)) {
                tmax = word;
            }
        }

        StdOut.println("std: " + max + " " + tst.get(max));
        StdOut.println("res: " + max + " " + st.get(max));

        StdOut.println("std: " + tst.get("business"));
        StdOut.println("res: " + st.get("business"));
    }

    public static void number() {
        Integer a = new Integer(22);
        Integer b = new Integer(22);

        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    public static void main(String[] args) {
        //frequencyCounter("E:\\data\\algs\\tale.txt", 8);
        //RedBlackBST.main(args);
        //LCS.main(args);
        //number();
        //CutFields.main(args);
        class Cell {
            int x, y;

            public Cell(int x, int y) {
                this.x = x;
                this.y = y;
            }
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (!(obj instanceof Cell)) {
                    return false;
                }
                Cell other = (Cell) obj;
                if (other.x == this.x && other.y == this.y) {
                    return true;
                }
                return false;
            }

            @Override
            public int hashCode(){
                return x*31 + y;
            }
        }
        HashMap<Cell, Integer> map = new HashMap<>();
        
        Cell a = new Cell(1, 2);
        Cell c = new Cell(1, 2);
        System.out.println(a == c);
        System.out.println(a.equals(c));
        map.put(a, 1);
        System.out.println(map.containsKey(c));
        
    }
}

