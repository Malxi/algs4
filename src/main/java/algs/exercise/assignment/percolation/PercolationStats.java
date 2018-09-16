package algs.exercise.assignment.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double lo;
    private final double hi;
    private final static double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        validate(n);
        validate(trials);
        double[] thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            thresholds[i] = experiments(n);
        }
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        lo = mean - CONFIDENCE_95*stddev/Math.sqrt(trials);
        hi = mean + CONFIDENCE_95*stddev/Math.sqrt(trials);
    }

    private void validate(int n) {
        if  (n <= 0) {
            throw new java.lang.IllegalArgumentException(); 
        }
    }

    private double experiments(int n) {
        Percolation pl = new Percolation(n);
        while (!pl.percolates()) {
            int row = 1 + StdRandom.uniform(n);
            int col = 1 + StdRandom.uniform(n);
            pl.open(row, col);
        }
        return (double) pl.numberOfOpenSites() / (n*n);
    }

    public double mean() {
        // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return lo;
    }
    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return hi;
    }
 
    public static void main(String[] args) {
        // test client (described below)
        int n = 100;
        int t = 200;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println("mean                    = " + ps.mean);
        StdOut.println("stddev                  = " + ps.stddev);
        StdOut.println("95% confidence interval = [" + ps.lo + "," + ps.hi + "]");
    } 
 }