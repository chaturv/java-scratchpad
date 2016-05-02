package com.chaturv.concurrent;

import java.util.LinkedList;
import java.util.Queue;

public class TestUnsafePolling implements Runnable {

	Queue<String> queue;
		
	
	public TestUnsafePolling(Queue<String> queue) {
		super();
		this.queue = queue;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList();
		queue.add("foo");
		queue.add("bar");
        TestUnsafePolling target = new TestUnsafePolling(queue);
        for (int i=0; i < 10; ++i) {
            Thread t = new Thread(target);
			t.start();
		}
	}

	@Override
	public void run() {
		for (int i=0; i < 100; ++i) {
			System.out.println(this.queue.poll());
		}
	}

}
