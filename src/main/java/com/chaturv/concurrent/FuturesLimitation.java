package com.chaturv.concurrent;

import java.util.concurrent.*;

/**
 * Created by Vineet on 1/8/2019.
 */
public class FuturesLimitation {

    private static ExecutorService executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

    /**
     * Demonstrate how futures can easily become blocking and prevent other work
     * from being performed asynchronously.
     */
    public static void run() throws Exception {
        try {
            // get f3 with dependent result from f1
            Future<String> f1 = executor.submit(new CallToRemoteServiceA());
            Future<String> f3 = executor.submit(new CallToRemoteServiceC(f1.get()));

            /* The work below can not proceed until f1.get() completes even though there is no dependency */

            // also get f4/f5 after dependency f2 completes
            Future<Integer> f2 = executor.submit(new CallToRemoteServiceB());
            Future<Integer> f4 = executor.submit(new CallToRemoteServiceD(f2.get()));
            Future<Integer> f5 = executor.submit(new CallToRemoteServiceE(f2.get()));

            System.out.println(f3.get() + " => " + (f4.get() * f5.get()));
        } finally {
            executor.shutdownNow();
        }
    }

    public static void runCompletable() throws Exception {
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        executor.submit(() -> {
            try {
                cf1.complete(new CallToRemoteServiceA().call());
            } catch (Exception e) {
                cf1.completeExceptionally(e);
            }
        });

        final CompletableFuture<String> cf3 = new CompletableFuture<>();
        cf1.thenAcceptAsync(serviceAResult -> {
            try {
                cf3.complete(new CallToRemoteServiceC(serviceAResult).call());
            } catch (Exception e) {
                cf3.completeExceptionally(e);
            }
        }, executor);

        CompletableFuture<Integer> cf2 = new CompletableFuture<>();
        executor.submit(() -> {
            try {
                cf2.complete(new CallToRemoteServiceB().call());
            } catch (Exception e) {
                cf2.completeExceptionally(e);
            }
        });

        CompletableFuture<Integer> cf4 = new CompletableFuture<>();
        cf2.thenAcceptAsync(serviceBResult -> {
            try {
                cf4.complete(new CallToRemoteServiceD(serviceBResult).call());
            } catch (Exception e) {
                cf4.completeExceptionally(e);
            }
        }, executor);

        CompletableFuture<Integer> cf5 = new CompletableFuture<>();
        cf2.thenAcceptAsync(serviceBResult -> {
            try {
                cf5.complete(new CallToRemoteServiceE(serviceBResult).call());
            } catch (Exception e) {
                cf5.completeExceptionally(e);
            }
        }, executor);

        //print result
        System.out.println(cf3.get() + " => " + (cf4.get() * cf5.get()));
        executor.shutdownNow();

    }

    public static void runCompletable_v2() throws Exception {
        Future<String> f1 = executor.submit(new CallToRemoteServiceA());
        Future<Integer> f2 = executor.submit(new CallToRemoteServiceB());

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                return new CallToRemoteServiceC(f1.get()).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executor);
        CompletableFuture<Integer> cf4 = CompletableFuture.supplyAsync(() -> {
            try {
                return new CallToRemoteServiceD(f2.get()).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executor);
        CompletableFuture<Integer> cf5 = CompletableFuture.supplyAsync(() -> {
            try {
                return new CallToRemoteServiceE(f2.get()).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executor);

        //print result
        System.out.println(cf3.get() + " => " + (cf4.get() * cf5.get()));
        executor.shutdownNow();

    }


    public static void main(String args[]) {
        try {
            long start = System.currentTimeMillis();
            runCompletable_v2();
//            run();
            System.out.println("Finished in: " + (System.currentTimeMillis() - start) + "ms");

//            run2();
//            run3();
//            run4();
//            run5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class CallToRemoteServiceA implements Callable<String> {
        @Override
        public String call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(200);
            return "responseA";
        }
    }

    private static final class CallToRemoteServiceB implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(100);
            return 100;
        }
    }

    private static final class CallToRemoteServiceC implements Callable<String> {

        private final String dependencyFromA;

        public CallToRemoteServiceC(String dependencyFromA) {
            this.dependencyFromA = dependencyFromA;
        }

        @Override
        public String call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(60);
            return "responseB_" + dependencyFromA;
        }
    }

    private static final class CallToRemoteServiceD implements Callable<Integer> {

        private final Integer dependencyFromB;

        public CallToRemoteServiceD(Integer dependencyFromB) {
            this.dependencyFromB = dependencyFromB;
        }

        @Override
        public Integer call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(140);
            return 40 + dependencyFromB;
        }
    }

    private static final class CallToRemoteServiceE implements Callable<Integer> {

        private final Integer dependencyFromB;

        public CallToRemoteServiceE(Integer dependencyFromB) {
            this.dependencyFromB = dependencyFromB;
        }

        @Override
        public Integer call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(55);
            return 5000 + dependencyFromB;
        }
    }
}
