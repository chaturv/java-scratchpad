package com.chaturv.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<Object> {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new MyCallable().test();

	}
	
	public void test() throws InterruptedException, ExecutionException {
		MyCallable myc = new MyCallable();
		Future f = Executors.newCachedThreadPool().submit(myc);
		f.get();
		f.cancel(true);
		
		
	}

	@Override
	public Object call() throws Exception {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("I got interrupted!");
		}
		return null;
	}

}
