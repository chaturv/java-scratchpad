package com.chaturv.locks;

import java.util.concurrent.locks.Lock;

import com.chaturv.datastructures.trees.misc.MyQueue;

public class Consumer implements Runnable{
	
	private MyQueue store;
	private LockService lockService;
	
	public Consumer(LockService lockService, MyQueue store) {
		this.lockService = lockService;
		this.store = store;
	}
	public void run() {
		while (true) {
			Lock lock = lockService.getLock();
			lock.lock();			
			try {
				while (store.isEmpty()) {
					try {
						lockService.hasCapacity().await();										
					} catch (InterruptedException e) {				
						e.printStackTrace();
					} 
				}
				store.dequeue();				
				//System.out.println("dequeued");
				lockService.noCapacity().signal();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
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
