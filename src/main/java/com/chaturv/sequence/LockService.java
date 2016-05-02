package com.chaturv.sequence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockService {

	private final Lock lock;
	private final Condition condition;		
		
	private LockService() {
		super();
		this.lock = new ReentrantLock();
		this.condition = lock.newCondition();
	}
	
	private static class LockServiceHolder {
		private static LockService lockService = new LockService();
	}
	
	public static LockService getInstance() {
		return LockServiceHolder.lockService;
	}
	
	public Lock getLock() {
		return lock;
	}
	public Condition getCondition() {
		return condition;
	}
	
	
	
}
