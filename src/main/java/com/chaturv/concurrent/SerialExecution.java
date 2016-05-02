package com.chaturv.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialExecution {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new SerialExecution().foo();

	}
	
	public void foo() throws InterruptedException {
		CountDownLatch readerCompleted = new CountDownLatch(1);
		ExecutorService service = Executors.newCachedThreadPool();
		
		service.submit(new Writer(readerCompleted));
		Thread.sleep(3000);
		service.submit(new Reader(readerCompleted));
		
		
	}
	
	

}

class Reader implements Runnable {
	
	CountDownLatch readerCompleted;	

	public Reader(CountDownLatch readerCompleted) {
		super();
		this.readerCompleted = readerCompleted;
	}



	@Override
	public void run() {
		System.out.println("Start reading");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done reading");
		readerCompleted.countDown();
		
	}
	
}

class Writer implements Runnable {

	CountDownLatch readerCompleted;
		
	public Writer(CountDownLatch readerCompleted) {
		super();
		this.readerCompleted = readerCompleted;
	}


	@Override
	public void run() {
		try {
			readerCompleted.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Writing done!");
		
		
	}
	
}