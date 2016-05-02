package com.chaturv.concurrent;

import java.util.LinkedList;

public class BlockingQueue {

	private LinkedList queue;
	private final int MAX;

	public BlockingQueue(int max) {
		queue = new LinkedList();
		this.MAX = max;
	}

	public void put(String s) {
		synchronized (queue) {
			while (queue.size() == MAX) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					System.out.println("I was interrupted while waiting to put!");
				}
			}
			queue.add(s);
			queue.notifyAll();
		}

	}

	public String get() {
		synchronized (queue) {
			while (queue.isEmpty()) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					System.out.println("I was interrupted while waiting to get!");
					return null;
				}
			}
			String s = (String) queue.removeFirst();
			queue.notifyAll();
			return s;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
