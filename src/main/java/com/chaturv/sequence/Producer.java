package com.chaturv.sequence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Callable {
	
	private Map<Integer, LinkedBlockingQueue<Trade>> idHashToQueue = new HashMap<Integer, LinkedBlockingQueue<Trade>>(); 
	
	public Producer(Map<Integer, LinkedBlockingQueue<Trade>> idHashToQueue) {
		super();
		this.idHashToQueue = idHashToQueue;
	}

	@Override
	public Object call() throws Exception {
		
		int tradeId = 0;
		int version = 0;

		for (int i = 0; i < 64; i++) {
			if (i % 8 == 0) {
				tradeId++;
				version = 1;
			}					

			Trade t = new Trade(tradeId, version, Math.random());			
			int key = tradeId % 8;
			LinkedBlockingQueue<Trade> queue = idHashToQueue.get(key);
			queue.put(t); //waits
			
			version++;
			Thread.sleep(1);
		}
		
		return null;

	}



	
	

}
