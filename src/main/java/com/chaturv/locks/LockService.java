package com.chaturv.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockService {
	
	private Lock lock = new ReentrantLock();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	public LockService() {
		
	}
	
	public Lock getLock() {
		return lock;
	}
	
	public Condition hasCapacity() {
		return notFull;
	}
	
	public Condition noCapacity() {
		return notEmpty;
	}

	public Lock getLock1() {
		return lock1;
	}

	public Lock getLock2() {
		return lock2;
	}


}
