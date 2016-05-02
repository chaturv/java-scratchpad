package com.chaturv.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class TestSumOfArray {

    public static void main(String[] args) {



        long[] arr = new long[2000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        long t0 = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++) {
            ForkJoinPool fjPool = new ForkJoinPool();
            fjPool.invoke(new SumOfArray(arr));

//            long result = 0;
//            for (long i : arr) {
//                result += i;
//            }

            //System.out.println(result);
        }
        System.out.println(System.currentTimeMillis() - t0);

    }
}

