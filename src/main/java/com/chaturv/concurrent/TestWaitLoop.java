package com.chaturv.concurrent;

import java.util.ArrayList;
import java.util.List;

public class TestWaitLoop {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		List l = new ArrayList();
		Thread t = new Thread(new LoopWorker(l));
		t.start();
		Thread.sleep(4000);
		synchronized (l) {
			//l.add("foo");
			l.notify();
		}
	}

}

class LoopWorker implements Runnable {

	List l;
	
	
	public LoopWorker(List l) {
		super();
		this.l = l;
	}
	
	@Override
	public void run() {
//		synchronized (l) {
//			while (l.isEmpty()) {
//				try {
//					l.wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println(l.get(0));
//		}
		
		synchronized (l) {
			while (l.isEmpty()) {
				try {
					System.out.println("Going to wait...");
					l.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(l.get(0));
		}
		
	}	
}