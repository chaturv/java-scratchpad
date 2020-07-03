package com.chaturv.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Vineet on 1/8/2019.
 */
public class FuturesStuff {

    public static void main(String[] args) {
        CompletableFuture<String> futureStage1 = new FuturesStuff().getAsyncResultCompletable();
        CompletableFuture<Integer> futureStage2 = futureStage1.thenApply(str -> str.length());

        CompletableFuture<Void> thenAcceptBoth = futureStage2.thenAcceptBoth(futureStage1, (len, str) -> {
            System.out.println(String.format("Length of %s is %d", str, len));
        });

//        try {
//            String result = futureStage1.get();
//            System.out.println(result);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public Future<String> getAsyncResult() {
        return Executors.newCachedThreadPool().submit(() -> {
            System.out.println("sleeping...");
            Thread.sleep(500);
            return "HelloWorld!";
        });
    }

    public CompletableFuture<String> getAsyncResultCompletable() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("sleeping...");
            Thread.sleep(500);
            completableFuture.complete("HelloWorld!");
            return null;
        });

        return completableFuture;
    }


}
