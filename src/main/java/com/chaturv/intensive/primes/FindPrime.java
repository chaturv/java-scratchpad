package com.chaturv.intensive.primes;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Find the n-th prime. Use a bad algo to make it CPU intensive
 *
 *  Notes:
 *  1. set tomcat worker threads = 1 (https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
 *  2. find # cores on the Gaia box
 *
 * Created by Vineet on 21/4/2016.
 */
public class FindPrime implements Runnable {

    private static final int MAX_THREADS = 8;
    private Integer N;

    public FindPrime(Integer n) {
        N = n;
    }

    public static void main(String[] args) {
        Integer N = Integer.valueOf(args[0]);
        FindPrime runnable = new FindPrime(N);

        System.out.println("Available Processors : " + Runtime.getRuntime().availableProcessors());

        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < MAX_THREADS; i++) {
            es.submit(runnable);
        }
        es.shutdown();
    }

    @Override
    public void run() {
        if (N == 1) {
            System.out.println("1 is a prime");
            return;
        }

        int count = 1;  //counting 1

        int start = 2; //start divisibility check at this
        int end = 2; //stop divisibility at this/2


        while (count < N) {
            boolean isPrime = true;
            end += 1; //increase end by 1 and start all over again
            for (int j = start; j <= end / 2; j++) {
                if (end % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
        }

        System.out.println(Thread.currentThread().getName() + " : The " + N + " (st/nd/rd/th) prime number is : " + end);
    }
}
