package com.chaturv.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class PingPong extends Thread {
    boolean read = false;
    Queue<String> queue;
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    final static Lock readLock = lock.readLock();
    final static Lock writeLock = lock.writeLock();
    boolean stop;

    public PingPong(boolean read, Queue<String> queue) {
        this.read = read;
        this.queue = queue;
    }

    int count = 0;

    @Override
    public String toString() {
        return "PingPong{" +
                "read=" + read +
                ", count=" + count +
                '}';
    }

    @Override
    public void run() {
        if (read) {
            while (!stop) {

                readLock.lock();
//                synchronized (queue) {
                try {

                    String string = queue.poll();
                    if (string != null) {
                        count++;
                    }
                } finally {
                    readLock.unlock();
                }


//                }
                inform();
            }
        } else {
            while (!stop) {

                writeLock.lock();
//                synchronized (queue) {
                try {
                    if (queue.add("some str" + count)) {
                        count++;
                    }
                } finally {
                    writeLock.unlock();
                }

//                }

                inform();
            }

        }


    }

    private void inform() {
//        Thread.yield();
//        synchronized (queue) {
//            queue.notify();
//            try {
//                queue.wait(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(1 % 2);
    	Queue<String> queue = new LinkedList();
//        queue = new ArrayBlockingQueue<String>(100);
//        queue = new ConcurrentLinkedQueue<String>();
        List<PingPong> pongs = new ArrayList<PingPong>();
        for (int i = 0; i < 10; ++i) {
            PingPong pingPong = new PingPong(i % 2 == 0, queue);
            pingPong.start();
            pongs.add(pingPong);
        }
        Thread.sleep(1000);
        int sum = 0;
        int read = 0;
        int write = 0;
        for (PingPong pp : pongs) {
            pp.stop = true;
            pp.join();
        }
        for (PingPong pp : pongs) {
            System.out.println(pp);
            sum += pp.count;
            if (pp.read) read += pp.count;
            else write += pp.count;
        }
        System.out.println(sum);
        System.out.println("write = " + write);
        System.out.println("read = " + read);
        System.out.println("queue.size() = " + queue.size());
        System.out.println("balance (must be zero) = " + (write - read - queue.size()));

    }
}