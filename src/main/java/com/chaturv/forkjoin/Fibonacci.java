package com.chaturv.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) { this.n = n; }

    public Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        ForkJoinPool fjPool = new ForkJoinPool();
        Integer result = fjPool.invoke(new Fibonacci(10));
        System.out.println(result);
    }
}
