package com.chaturv.sequence;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Callable {

	private LinkedBlockingQueue<Trade> queue;
	private StatsCollector statsCollector;		

	public Consumer(LinkedBlockingQueue<Trade> queue,
			StatsCollector statsCollector) {
		super();
		this.queue = queue;
		this.statsCollector = statsCollector;
	}

	@Override
	public Object call() throws Exception {						
		while (true) {
			Trade trade = queue.take(); //waits
			process(trade);
		}				
	}

	private BigDecimal process(Trade trade) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Processsed trade: " + trade.getTradeId() + ":" + trade.getVersion());
		statsCollector.add(trade.getTradeId(), trade.getVersion());
		
		return new BigDecimal(trade.getData()).multiply(BigDecimal.valueOf(100));
	}

}
