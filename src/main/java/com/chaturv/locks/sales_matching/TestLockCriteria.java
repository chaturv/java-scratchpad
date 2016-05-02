package com.chaturv.locks.sales_matching;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class TestLockCriteria {

    static final LockCriteria criteria = new LockCriteria("silverda", new BigDecimal(100));
    static final CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {


        Thread t1 = new Thread(new Matcher());
        Thread t2 = new Thread(new Matcher());

        t1.start();
        t2.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main done");
    }
}


class Matcher implements Runnable {

    @Override
    public void run() {

        Lock lock = LockService.getInstance().getLock(TestLockCriteria.criteria);
        lock.lock();
        System.out.println("got lock. sleeping now");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("released lock.");
        TestLockCriteria.latch.countDown();
    }
}