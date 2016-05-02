package com.chaturv.concurrent;

public class TestDifferentObjects implements Runnable{


    public static void main(String[] args) {
        TestDifferentObjects target = new TestDifferentObjects();

        Thread t1 = new Thread(target);
        Thread t2 = new Thread(target);

        t1.start();
        t2.start();
    }

    public synchronized void foo() {
        System.out.println(Thread.currentThread().getName() + " In. Now waiting...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Done waiting.");

    }


    @Override
    public void run() {
        foo();
    }
}

