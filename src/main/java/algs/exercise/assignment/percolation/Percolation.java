package algs.exercise.assignment.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufWithOutBottom;
    private boolean[][] opened;
    private int cnt = 0;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        validate(n);
        /*
        id = new int[n*n + 2];
        id[0] = 0;
        id[n*n+1] = n*n+1;
        sz = new int[n*n + 2];
        sz[0] = 1;
        sz[n*n+1] = 1;
        */
        uf = new WeightedQuickUnionUF(n*n+2);
        ufWithOutBottom = new WeightedQuickUnionUF(n*n+2);
        opened = new boolean[n][n];
        cnt = 0;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > opened.length || col < 1 || col > opened.length) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void validate(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException(); 
        }
    }

    private int convert(int row, int col) {
        int n = opened.length;
        if (row == 0) {
            return 0;
        }
        if (row == n + 1) {
            return n*n + 1;
        }
        if (row > 0 && row < n + 1 && col > 0 && col < n + 1) {
            return (row - 1)*n + (col-1) + 1;
        }
        return -1;
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        validate(row, col);
        if (isOpen(row, col)) return;

        int n = opened.length;

        // open operation
        int p = convert(row, col);
        opened[row-1][col-1] = true;
        cnt++;

        // union the neighbor
        int q;
        q = convert(row - 1, col);
        if (q == 0) {
            uf.union(p, q);
            ufWithOutBottom.union(p, q);
        }
        else if (row > 1 && isOpen(row - 1, col)) {
            uf.union(p, q);
            ufWithOutBottom.union(p, q);
        }

        q = convert(row + 1, col);
        if (q == n*n + 1) {
            uf.union(p, q);
        }
        else if (row < n && isOpen(row + 1, col)) {
            uf.union(p, q);
            ufWithOutBottom.union(p, q);
        }

        q = convert(row, col - 1);
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(p, q);
            ufWithOutBottom.union(p, q);
        }

        q = convert(row, col + 1);
        if (col < n && isOpen(row, col + 1)) {
            uf.union(p, q);
            ufWithOutBottom.union(p, q);
        }

    }    
    
    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        validate(row, col);
        return opened[row - 1][col-1];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        validate(row, col);
        int p = convert(row, col);
        
        if (isOpen(row, col))
            return ufWithOutBottom.find(0) == ufWithOutBottom.find(p);
        else
            return false;
    }
    
    public int numberOfOpenSites() {
        // number of open sites
        return cnt;
    }
    
    public boolean percolates() {
        // does the system percolate?
        int n = opened.length;
        return uf.find(0) == uf.find(n*n+1);
    }             
 
    public static void main(String[] args) {
        // test client (optional)
        Percolation pl = new Percolation(3);
        pl.open(1, 1);
        StdOut.println("open cnt: " + pl.cnt + ", is full: " + pl.isFull(1, 1) + ", percolates: " + pl.percolates());
        pl.open(2, 2);
        StdOut.println("open cnt: " + pl.cnt + ", is full: " + pl.isFull(2, 2) + ", percolates: " + pl.percolates());
        pl.open(3, 3);
        StdOut.println("open cnt: " + pl.cnt + ", is full: " + pl.isFull(3, 3) + ", percolates: " + pl.percolates());
        pl.open(1, 2);
        StdOut.println("open cnt: " + pl.cnt + ", is full: " + pl.isFull(2, 2) + ", percolates: " + pl.percolates());
        pl.open(3, 2);
        StdOut.println("open cnt: " + pl.cnt + ", is full: " + pl.isFull(3, 2) + ", percolates: " + pl.percolates());
    }
 }