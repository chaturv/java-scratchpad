package com.chaturv.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestSequence {

	public static void main(String[] args) throws InterruptedException {
		new TestSequence().test();
	}

	public void test() throws InterruptedException {
		ExecutorService execService = Executors.newFixedThreadPool(16);
		StatsCollector statsCollector = new StatsCollector();
		
		Map<Integer, LinkedBlockingQueue<Trade>> idHashToQueue = new HashMap<Integer, LinkedBlockingQueue<Trade>>();
		Collection<Consumer> consumers = new ArrayList<Consumer>();
				
		for (int i = 0; i < 8; i++) {
			LinkedBlockingQueue<Trade> queue = new LinkedBlockingQueue<Trade>();
			
			Consumer consumer = new Consumer(queue, statsCollector);
			consumers.add(consumer);
			
			idHashToQueue.put(i, queue);
		}
		
		Producer producer = new Producer(idHashToQueue);		
		

		for (Consumer consumer : consumers) {
			execService.submit(consumer);
		}

        Thread.sleep(5 * 1000);
        execService.submit(producer);
        System.out.println("producer started!");

		Thread.sleep(15 * 1000);
		System.out.println("waking up!");
		
		
		Map<Integer, List<Integer>> stats = statsCollector.getM();
		for (Integer tradeId : stats.keySet()) {
			System.out.print("trade id=[" + tradeId + "] Processing order: ");
			List<Integer> list = stats.get(tradeId);
			for (Integer integer : list) {				
				System.out.print(integer+",");
			}
			System.out.println();
		}
		
		
		
		execService.shutdown();
	}

}
