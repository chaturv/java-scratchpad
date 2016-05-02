package com.chaturv.locks;

import com.chaturv.datastructures.trees.misc.MyQueue;

public class TestProducerConsumer {
	
	public static void main(String[] args) {
		LockService lockService = new LockService();
		MyQueue myQueue = new MyQueue();
		int limit = 20;

        Producer target = new Producer(lockService, myQueue, limit);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(target);
			t.start();
		}

        Consumer consumer = new Consumer(lockService, myQueue);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(consumer);
			t.start();
		}
		
		
	}

}
