package com.chaturv.collections;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TicksService {

    private static final int MAX = 10;
    private LinkedList<BigDecimal> ticks = new LinkedList<BigDecimal>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    private Lock emptyLock = new ReentrantLock();
    private Condition notFull = emptyLock.newCondition();


    public void acceptTick(BigDecimal tick) {

        writeLock.lock();

        if (ticks.size() >= MAX) {
            ticks.removeLast();
        }
        ticks.offerFirst(tick);


        emptyLock.lock();
        notFull.signalAll();
        emptyLock.unlock();

        writeLock.unlock();
    }

    public BigDecimal getLatest() {

        while (ticks.isEmpty()) {
            emptyLock.lock();
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            emptyLock.unlock();
        }

        readLock.lock();
        BigDecimal tick = ticks.peekFirst();
        readLock.unlock();
        return tick;
    }
}
