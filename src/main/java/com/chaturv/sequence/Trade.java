package com.chaturv.sequence;

public class Trade {

	private int tradeId;
	
	private int version;
	
	private double data;

	public Trade(int tradeId, int version, double data) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.data = data;
	}

	public int getTradeId() {
		return tradeId;
	}

	public int getVersion() {
		return version;
	}

	public double getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", data="
				+ data + "]";
	}			

}
