package algs.exercise.chapter1.section5;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

public class UF{

    private int[] id;
    private int[] sz;
    private int[] hight;
    private int count;

    public UF(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for(int i = 0; i < N; i++)
            sz[i] = i;
        hight = new int[N];
        for(int i = 0; i < N; i++)
            hight[i] = 1;
    }

    public void union(int p, int q){
        //weightedQuickUnion(p, q);
        //quickUnion(p, q);
        hightQuickUnion(p, q);
    }

    public void slowerUnion(int p, int q){
        int pID = find(p);
        int qID = find(q);

        if(pID == qID) return;

        for(int i = 0; i < id.length; i++){
            if(id[i] == pID) id[i] = qID;
        }
        count--;
    }

    public int find(int p){
        //return quickFind(p);
        //while(p != id[p]) p = id[p];
        //return p;
        return compressionFind(p);
    }

    public int compressionFind(int p){
        int root = p;
        while(root != id[root]){
            root = id[root];
        }
        while(p != root){
            int pp = id[p];
            id[p] = root;
            p = pp;
        }
        return root;
    }

    public int quickFind(int p){
        return id[p];
    }

    public void quickUnion(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        id[pRoot] = id[qRoot];
        count--;
    }

    public void weightedQuickUnion(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        if(sz[pRoot] < sz[qRoot]){
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public void hightQuickUnion(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        if(hight[pRoot] < hight[qRoot]){
            id[pRoot] = qRoot;
        }else if(hight[pRoot] > hight[qRoot]){
            id[qRoot] = pRoot;
        }else{
            id[qRoot] = pRoot;
            hight[pRoot]++;
        }
        count--;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        int[] p = {4,3,6,9,2,8,5,7,6,1,6};
        int[] q = {3,8,5,4,1,9,0,2,1,0,7};
        UF uf = new UF(p.length);
        for(int i = 0; i < p.length; i++){
            if(uf.connected(p[i], q[i])){
                continue;
            }
            uf.union(p[i], q[i]);
            StdOut.println(p[i] + " " + q[i]); 
        }
    }
}