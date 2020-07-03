package com.chaturv.reactive;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.reactivex.Observable.fromArray;



/**
 * Created by Vineet on 2/8/2019.
 */
public class ReactiveStuffRxJava2 {

    public static void main(String[] args) throws InterruptedException {
        new ReactiveStuffRxJava2().rx2Completeable();
    }

    private void simpleArray() {
        //create
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Observable<Integer> observable = Observable.fromIterable(integers);
        ConnectableObservable<Integer> connectableObservable = observable.publish();

        //subscribe
        connectableObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("onSubscribe called for disposable resource: " + disposable);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext called. Received: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("onError called. Received: " + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete called.");
            }
        });

        System.out.println("Calling connect on ConnectableObservable.");
        connectableObservable.connect();
    }

    //TODO: Appears rsync util should be deprecated - it is bringing in rx1 dependency? It is it now available in rxjava2?
    private void asyncUtilStart() {

    }

    public void syncOrAsync() {
        ConnectableObservable<Integer> observable = fromArray(1).publish();
        //#1
        observable.subscribe(integer -> {
            System.out.println("Subscriber #1. Thread is: " + Thread.currentThread().getName());
            System.out.println("Subscriber #1. Sleeping...");
            Thread.sleep(2000);
            System.out.println("Subscriber #1. The number is: " + integer);
        });
        //#2
        observable.subscribe(integer -> {
            System.out.println("Subscriber #2. Thread is: " + Thread.currentThread().getName());
            System.out.println("Subscriber #2. The number is: " + integer);
        });

        observable.connect();
    }

    private void unicastSubject() throws InterruptedException {
        List<Integer> integers = IntStream.range(0, 10).boxed().collect(Collectors.toList());

//        Observable<Integer> observable = Observable.fromIterable(integers);//.publish();
        Observable<Integer> observable = Observable.fromArray(1,2,3,4,5);//.publish();
//        UnicastSubject<Integer> unicastSubject = UnicastSubject.create();
//        observable.subscribe(unicastSubject);

//        System.out.println("Called: observable.connect();");
//        observable.connect();

//        System.out.println("Sleeping...");
//        Thread.sleep(2000);

//        System.out.println("Subscribing consumer #1.");
        Consumer<Integer> integerConsumer = integer -> System.out.println("The value is: " + integer);
        Consumer<Integer> integerConsumer2 = integer -> System.out.println("The value * 2  is: " + integer * 2);
        Consumer<Throwable> integerErrConsumer = throwable -> System.out.println("The error is: " + throwable);
        Action onComplete = () -> System.out.println("OnComplete called");
//        unicastSubject.subscribe(integerConsumer);
//        unicastSubject.subscribe(integerConsumer, integerErrConsumer);

        System.out.println("Subscribing consumer #1.");
        observable.subscribe(integerConsumer, integerErrConsumer, onComplete);

        System.out.println("Sleeping...");
        Thread.sleep(2000);

        System.out.println("Subscribing consumer #2.");
        observable.subscribe(integerConsumer2, integerErrConsumer, onComplete);
    }

    private void rx2BasicExample() {
//        Flowable.fromArray("foobar").subscribe(s -> System.out.println("Hello " + s + "!"));
        Flowable<Integer> integerFlowable = Flowable.fromCallable(() -> Math.max(3, 6));


//        Flowable<Integer> integerFlowable = Flowable.fromIterable(integers);
//        integerFlowable.subscribe(integer -> {
//            System.out.println("got an integer: " + integer);
//        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void rx2BackPressureExample() throws InterruptedException {
        Flowable<Integer> integerFlowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            Iterator<Integer> integers = IntStream.range(0, 100000).boxed().collect(Collectors.toList()).iterator();
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                try {
                    while (integers.hasNext()) {
                        Integer value = integers.next();
                        if (value == -1) {
                            throw new RuntimeException("5 is never good.");
                        }
                        emitter.onNext(value);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }

//                emitter.setDisposable(new Disposable() {
//                    @Override
//                    public void dispose() {
//                        System.out.println("We can dispose of some stuff here");
//                    }
//
//                    @Override
//                    public boolean isDisposed() {
//                        return true;
//                    }
//                });
            }
        }, BackpressureStrategy.ERROR).observeOn(Schedulers.computation());

        Disposable disposable = integerFlowable.subscribe(
                integer -> {System.out.println(Thread.currentThread().getName() + " -- got an integer: " + integer); /*Thread.sleep(1);*/},
                t -> System.out.println(Thread.currentThread().getName() + " -- got an error: " + t),
                () -> System.out.println(Thread.currentThread().getName() + " -- things are complete"));

        while (!disposable.isDisposed()) {
            System.out.println(Thread.currentThread().getName() + " -- Not disposed yet. Sleeping...");
            Thread.sleep(5_000);
        }
    }

    private void rx2Maybe() throws InterruptedException {
        Disposable d = Maybe.just("Hello World")
//                .delay(4, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("Started");
                    }

                    @Override
                    public void onSuccess(String value) {
                        System.out.println("Success: " + value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        Thread.sleep(6000);
        d.dispose();
    }

    /**
     * A completeable which sends notification that two services of interest have been shutdown
     */
    private void rx2Completeable() {
        //create services
        List<String> serviceNames = Arrays.asList("gem-a", "gem-b");
        List<Service> services = createServices(serviceNames);
        //start services
        services.stream().forEach(Service::start);

        //create a scheduler which will stop the services after a certain time
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        List<? extends ScheduledFuture<?>> futures = services.stream()
                .map(service -> scheduledExecutor.schedule(service::stop, 3, TimeUnit.SECONDS))
                .collect(Collectors.toList());

        List<Completable> completables = futures.stream()
                .map(Completable::fromFuture)
                .collect(Collectors.toList());
        Completable mergedCompletable = Completable.merge(completables);

        mergedCompletable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                System.out.println("All services have been shutdown");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        mergedCompletable.blockingAwait();

        scheduledExecutor.shutdown();
    }

    private List<Service> createServices(List<String> serviceNames) {
        return serviceNames.stream()
                .map(Service::new)
                .collect(Collectors.toList());
    }

    public class Service {
        private String serviceName;
        private boolean shutdown;

        public Service(String serviceName) {
            this.serviceName = serviceName;
        }

        public void start() {
            System.out.println(String.format("Service [%s] has been started.", serviceName));
        }

        public void stop() {
            shutdown = true;
            System.out.println(String.format("Service [%s] has been shutdown.", serviceName));
        }

        public boolean isShutdown() {
            return shutdown;
        }
    }
}
