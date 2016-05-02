package com.chaturv.unionfind;

import java.util.Arrays;

/**
 * Based on tree
 */
public class UnionFind {

    private int[] array;
    private int[] sz;

    public UnionFind(int size) {
        array = new int[size];
        sz = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int root_p = root(p);
        int root_q = root(q);

        if (root_p == root_q) {
            return;
        }

        //root change by factoring in balancing
        if (sz[root_p] > sz[root_q]) {
            array[root_q] = root_p;
            sz[root_p] += sz[root_q];

        } else {
            array[root_p] = root_q;
            sz[root_q] += sz[root_p];
        }

    }

    public boolean find(int p, int q) {
        boolean b = root(p) == root(q);
        System.out.println(p + " and " + q + " connected? : " + b);

        return b;
    }

    private int root(int p) {
        while (p != array[p]) {
            array[p] = array[array[p]]; //path compression
            p = array[p];
        }

        return p;
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(0,1);
        uf.union(1,2);
        uf.union(2,3);
        uf.union(3,4);
        uf.union(4,5);

        uf.printArray();

        uf.union(5,6);
        uf.union(6,7);
        uf.union(7,8);
        uf.union(8,9);

        uf.find(0,9);

        uf.printArray();
    }
}