package com.chaturv.locks.sales_matching;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockService {

    private Semaphore semaphore = new Semaphore(1);
    private Map<LockCriteria, Lock> locks = new HashMap<LockCriteria, Lock>();


    private LockService() {

    }

    public Lock getLock(LockCriteria criteria) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        Lock lock = locks.get(criteria);
        if (lock == null) {
            lock = new ReentrantLock();
            locks.put(criteria, lock);
        }

        semaphore.release();

        return lock;
    }


    public static LockService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static LockService INSTANCE = new LockService();
    }
}
