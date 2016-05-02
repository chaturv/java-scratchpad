package com.chaturv.sequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsCollector {

	Map<Integer, List<Integer>> m = new HashMap<Integer, List<Integer>>();

	public void add(int tradeId, int version) {
		List<Integer> list = m.get(tradeId);
		if (list == null) {
			list = new ArrayList<Integer>();
			m.put(tradeId, list);
		}
		list.add(version);
	}

	public Map<Integer, List<Integer>> getM() {
		return m;
	}
	
	

}
