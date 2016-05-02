package com.chaturv.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadExceptions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ThreadExceptions().foo();

	}
	
	public void foo() {
		ExecutorService service = Executors.newFixedThreadPool(1);
		Future<String> future = service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				throw new RuntimeException("foo");
				
			}
		});
		
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				throw new RuntimeException("foo");				
			}
		});
		
//		try {
//			future.get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			System.out.println("here...");
//			e.printStackTrace();
//		}
	}

}
