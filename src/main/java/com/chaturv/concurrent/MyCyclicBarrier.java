package com.chaturv.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrier {

	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(10);
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 1; i <= 10; i++) {
			service.submit(new aWorker(barrier, i * 500));
		}

	}
	
	

}

class aWorker implements Callable {		
	
	CyclicBarrier barrier;
	int waitTime;
	
	


	public aWorker(CyclicBarrier barrier, int waitTime) {
		super();
		this.barrier = barrier;
		this.waitTime = waitTime;
	}




	@Override
	public Object call() throws Exception {
		System.out.println("I wait..");
		Thread.sleep(waitTime);
		
		barrier.await();
		System.out.println("I done!");
		return null;
	}
	
}