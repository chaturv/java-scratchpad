package com.chaturv.unionfind;

import org.junit.Before;
import org.junit.Test;

public class TestUnionFind {

    @Before
    public void init() {
        System.out.println("Before...");
    }

    @Test
    public void union() {
        UnionFind uf = new UnionFind(2);
        uf.union(0,1);
        uf.union(2,3);
        boolean b = uf.find(1,0);

        org.junit.Assert.assertTrue("0 and 1 not connected", b);
    }
}
