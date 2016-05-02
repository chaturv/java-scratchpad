package com.chaturv.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SumOfArray extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10;
    private long[] arr;

    public SumOfArray(long[] arr) {
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (arr.length < THRESHOLD) {
            return computeDirectly(arr);
        }

        int mid = arr.length >>> 1;
        long[] dest1 = new long[mid];
        long[] dest2 = new long[arr.length - mid];

        System.arraycopy(arr, 0, dest1, 0, dest1.length);
        System.arraycopy(arr, mid, dest2, 0, dest2.length);

        SumOfArray s1 = new SumOfArray(dest1);
        SumOfArray s2 = new SumOfArray(dest2);

        s1.fork();
        return s2.compute() + s1.join();
    }

    private long computeDirectly(long[] arr) {
        long sum = 0;
        for (long i : arr) {
            sum += i;
        }
        return sum;
    }
}
