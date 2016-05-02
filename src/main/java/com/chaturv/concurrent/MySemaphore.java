package com.chaturv.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {

	private final int MAX_PERMITS;
	private int available;
	private Lock lock = new ReentrantLock();
	private Condition noAvailability = lock.newCondition();
		
	public MySemaphore(int maxPermits) {
		super();
		this.MAX_PERMITS = available = maxPermits;		
	}

	public void aquire() {
		lock.lock();			
		while (available == 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " Not available. Waiting...");
				noAvailability.await();
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " Got a permit!");
		available--;
		System.out.println("#: " + available);
		lock.unlock();
	}
	
	public void release() {
		lock.lock();
		if (available < MAX_PERMITS) {
			available++;			
			System.out.println(Thread.currentThread().getName() + " Released a permit!");
		}
		System.out.println("#: " + available);
		noAvailability.signal();
		lock.unlock();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MySemaphore sem = new MySemaphore(3);
		for (int i = 0; i < 9; i++) {
			Thread t = new Thread(new MyWorker(sem));
			t.start();			
		}

	}
}

class MyWorker implements Runnable {

	MySemaphore sem;
	
	
	public MyWorker(MySemaphore sem) {
		super();
		this.sem = sem;
	}


	@Override
	public void run() {
		sem.aquire();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}
		sem.release();				
	}		
}