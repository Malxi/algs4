// package algs.collection;

import java.util.HashMap;
import java.util.Iterator;

class VanEmdeBoasTree {
    private static final int BASE = 2;
    private int u;
    private int min;
    private int max;
    private VanEmdeBoasTree summary;
    private HashMap<Integer, VanEmdeBoasTree> clusters;

    private int sqrtU() {
        return (int) Math.ceil(Math.sqrt(u));
    }

    private int high(int x) {
        return x / sqrtU();
    }

    private int low(int x) {
        return x % sqrtU();
    }

    private int index(int i, int j) {
        return i * sqrtU() + j;
    }

    private VanEmdeBoasTree lazyCreateCluster(int i) {
        // layze initialization to save space
        if (!this.clusters.containsKey(i)) {
            VanEmdeBoasTree c = new VanEmdeBoasTree(sqrtU());
            this.clusters.put(i, c);
            return c;
        }
        return this.clusters.get(i);
    }

    private int cluserMin(int i) {
        if (!this.clusters.containsKey(i)) {
            return -1;
        }
        return this.clusters.get(i).min;
    }

    private int cluserMax(int i) {
        if (!this.clusters.containsKey(i)) {
            return -1;
        }
        return this.clusters.get(i).min;
    }

    VanEmdeBoasTree(int size) {
        this.u = size;
        this.min = -1;
        this.max = -1;
        if (this.u > BASE) {
            this.summary = new VanEmdeBoasTree(sqrtU());
            this.clusters = new HashMap<Integer, VanEmdeBoasTree>();
        }
    }

    public boolean contains(int x) {
        if (x < 0 || x >= this.u) {
            return false;
        }
        if (this.min == x || this.max == x) {
            return true;
        }
        if (this.u == BASE) {
            return false;
        }
        if (clusters.containsKey(high(x))) {
            return this.clusters.get(high(x)).contains(low(x));
        }
        return false;
    }

    public void insert(int x) {
        // Lazy Propagation
        // We store all values in min && max
        // The leaf node has size 2, which means it can store two values: min and max
        // We first put the min value in the min, if the min value changes, need to Propagation old min down
        if (this.min == -1) {
            this.min = x;
            this.max = x;
            return;
        }
        if (x < this.min) {
            // swap to propagate
            int t = this.min;
            this.min = x;
            x = t;
        }
        if (x > this.max) {
            this.max = x;
        }
        // leaf node
        if (this.u == BASE) {
            return;
        }
        if (lazyCreateCluster(high(x)).min == -1) {
            this.summary.insert(high(x));
        }
        lazyCreateCluster(high(x)).insert(low(x));
    }

    public void delete(int x) {
        if (this.contains(x)) {
            rdelete(x);
        }
    }

    // TODO: optimize
    private void rdelete(int x) {
        // base case
        if (this.u == BASE) {
            // only one value
            if (x == this.min && x == this.max) {
                this.min = -1;
                this.max = -1;
            }
            if (x == this.min) {
                this.min = this.max;
            }
            if (x == this.max) {
                this.max = this.min;
            }
            return;
        }
        if (x == this.min) {
            int i = this.summary.min;
            if (i == -1) {
                this.min = -1;
                this.max = -1;
                return;
            }
            // we re propagate min from bottom
            x = index(i, this.cluserMin(i));
            this.min = x;
        }
        if (this.clusters.containsKey(high(x))) {
            this.clusters.get(high(x)).delete(low(x));
        }
        // cluser min is deleted
        // delete min from summary
        if (cluserMin(high(x)) == -1) {
            this.summary.delete(high(x));;
        }
        // max is updated from bottom
        if (x == this.max) {
            // Get the max from 
            int i = this.summary.max;
            if (i == -1) {
                this.max = this.min;
                return;
            }
            this.max = index(i, cluserMax(i));
        }
        return;
    }

    public int successor(int x) {
        // base case
        if (this.u == BASE) {
            if (x < this.min) return this.min;
            if (x < this.max) return this.max;
            else return -1;
        }

        // this veb is empty
        if (this.min == -1) return -1;

        // x < min < max
        if (x < this.min) return this.min;
        
        int i = high(x);
        int j = 0;
        // min <= x < cluster[i].max
        if (this.clusters.containsKey(i) && low(x) < this.clusters.get(i).max) {
            j = this.clusters.get(i).successor(low(x));
        }
        // min <= cluster[i].max <= x
        else {
            i = this.summary.successor(i);
            if (i == -1) {
                return -1;
            }
            j = this.clusters.get(i).min;
        }
        return index(i, j);
    }

    public int preSuccessor(int x) {
        // base case
        if (this.u == BASE) {
            if (x > this.max) return this.max;
            if (x > this.min) return this.min;
            else return -1;
        }

        // this veb is empty
        if (this.min == -1) return -1;

        // min < max < x
        if (x > this.max) return this.max;
        
        int i = high(x);
        int j = 0;
        // clusrers[i].min < x <= max
        if (this.clusters.containsKey(i) && low(x) > this.clusters.get(i).min) {
            j = this.clusters.get(i).preSuccessor(low(x));
        }
        // min < x <= clusrers[i].min <= max
        else {
            i = this.summary.preSuccessor(i);
            if (i == -1) {
                // lazy propagation
                if (x > this.min) return this.min;
                else return -1;
            }
            j = this.clusters.get(i).max;
        }
        return index(i, j);
    }
    

    public static void main(String[] args) {
        VanEmdeBoasTree veb = new VanEmdeBoasTree(16);
        System.out.println("insert");
        for(int i = 15; i >=0; i--) {
                veb.insert(i);
        }
        for(int i = 15; i >=0; i--) {
                System.out.printf("successor(%d)=%d, preSuccessor(%d)=%d\n", i, veb.successor(i), i, veb.preSuccessor(i));
        }
        System.out.println("delete");
        for(int i = 15; i >=0; i-=2) {
            veb.delete(i);
        }
        for(int i = 15; i >=0; i--) {
            System.out.printf("successor(%d)=%d, preSuccessor(%d)=%d\n", i, veb.successor(i), i, veb.preSuccessor(i));
    }
    }
}

