package com.chaturv.locks;

import java.util.concurrent.locks.Lock;

import com.chaturv.datastructures.trees.misc.MyQueue;

public class Producer implements Runnable{
	
	private MyQueue store;
	private LockService lockService;
	private int limit;
	
	public Producer(LockService lockService, MyQueue store, int limit) {
		this.lockService = lockService;
		this.store = store;
		this.limit = limit;
	}
	
	public void run() {
		while (true) {
			String str = new String();
			Lock lock = lockService.getLock();
			lock.lock();
			try {
				while (store.size() >= limit) {				
					try {
						lockService.noCapacity().await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}							
				}
				store.enqueue(str);
				//System.out.println("enqueued");
				lockService.hasCapacity().signal();
			} finally {
				lock.unlock();
			}
			
		}		
		
	}

	public LockService getLockService() {
		return lockService;
	}

	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

}
