package com.chaturv.datastructures.trees.misc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {
	
	public static void main(String[] args) {
		Ticket t = new Ticket(10);
		BulkTicket bt = new BulkTicket(5);
		
		PriorityQueue<Ticket> pq = new PriorityQueue<Ticket>(2, new TicketComparator());
		pq.add(t);
		pq.add(bt);
		
		System.out.println(pq.peek().getClass());			
	}

}

class Ticket {
	int priority;

	public Ticket(int priority) {
		super();
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}		
	
}


class BulkTicket extends Ticket {

	public BulkTicket(int priority) {
		super(priority);		
	}
	
}

class TicketComparator implements Comparator<Ticket> {

	@Override
	public int compare(Ticket t1, Ticket t2) {		
		return -1 * (t1.getPriority() - t2.getPriority());
	}
	
}