package com.chaturv.sequence;

import java.math.BigDecimal;

public class JMSMessage implements Comparable<JMSMessage>{

	private final long timestamp;
	
	private Trade trade;

	public JMSMessage(long timestamp, Trade trade) {
		super();
		this.timestamp = timestamp;
		this.trade = trade;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Trade getTrade() {
		return trade;
	}

	@Override
	public int compareTo(JMSMessage o) {	
		return new BigDecimal(this.timestamp).compareTo(new BigDecimal(o.timestamp));
	}

	@Override
	public String toString() {
		return "JMSMessage [timestamp=" + timestamp + ", trade=" + trade + "]";
	}		
	
}
