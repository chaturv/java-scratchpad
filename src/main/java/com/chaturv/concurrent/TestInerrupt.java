package com.chaturv.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestInerrupt {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future f = executorService.submit(new Worker());
		Thread.sleep(2 * 1000);
		System.out.println("cancel now!");
		f.cancel(true
				);
		executorService.shutdown();
		System.out.println("hsutting down");
//		try {
//			f.get();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
	}
	

}

class Worker implements Runnable {

	@Override
	public void run() {
//		try {
//			Thread.sleep(5 * 1000);
//			System.out.println("I am done!");
//		} catch (InterruptedException e) {
//			System.out.println("I was interrupted!");
//			//e.printStackTrace();
//		}
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			
//		}
		while (true) {
			if (Thread.interrupted()) {
				System.out.println("hey i was interrupted!");
				break;
			}
		}
	}	
}
