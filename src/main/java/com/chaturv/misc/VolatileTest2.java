package com.chaturv.misc;

public class VolatileTest2 implements Runnable{

    private volatile boolean stopped = false;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest2 target = new VolatileTest2();
        new Thread(target).start();

        Thread.sleep(5 * 1000);
        target.setStopped();
    }

    public synchronized void setStopped() {
        stopped = true;
        notifyAll();
        System.out.println("notified");
    }

    @Override
    public synchronized void run() {
        while (!stopped) {
            System.out.println("started server");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("stopped server");
    }
}
