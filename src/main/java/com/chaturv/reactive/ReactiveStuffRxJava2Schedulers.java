package com.chaturv.reactive;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Vineet on 10/8/2019.
 */
public class ReactiveStuffRxJava2Schedulers {

    public static void main(String[] args) throws InterruptedException {
        new ReactiveStuffRxJava2Schedulers().subscribeOn();
    }

    private void subscribeOn() throws InterruptedException {
        List<Integer> integers = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        Function<Integer, Integer> mapOperator = integer -> {
            System.out.println("\nMapOperator. Thread is: " + Thread.currentThread().getName());
            return integer * 2;
        };

        ConnectableObservable<Integer> observable = Observable
                .fromIterable(integers)
                .subscribeOn(Schedulers.computation())
                .map(mapOperator)
                .observeOn(Schedulers.computation())
                .publish();

        Consumer<Integer> integerConsumer = integer -> {
            System.out.print("\nIntegerConsumer. Thread is: " + Thread.currentThread().getName());
            System.out.println(". Received value: " + integer);
        };
        Consumer<Throwable> integerErrConsumer = throwable -> System.out.println("The error is: " + throwable);
        Action onComplete = () -> System.out.println("OnComplete called");

        Disposable observer = observable.subscribe(
                integerConsumer, integerErrConsumer, onComplete
        );

        observable.connect();

        while (!observer.isDisposed()) {
            System.out.println("Main sleeping...");
            Thread.sleep(100);
        }
        System.out.println("Main done.");
    }
}
