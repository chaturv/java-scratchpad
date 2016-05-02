package com.chaturv.piped;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumberGenLockService {
	
	Lock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	
	private static NumberGenLockService self = new NumberGenLockService(); 
	
	public Lock getLock() {
		return lock;
	}
	public Condition getNotFull() {
		return notFull;
	}
	
	public static NumberGenLockService getInstance() {
		return self;
	}	
}
