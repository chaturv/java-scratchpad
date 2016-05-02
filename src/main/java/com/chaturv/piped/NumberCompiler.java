package com.chaturv.piped;

import java.util.LinkedList;

public class NumberCompiler implements Runnable {

	private LinkedList<Integer> oddList;
	private LinkedList<Integer> evenList;
	
	
	
	public NumberCompiler(LinkedList<Integer> oddList, LinkedList<Integer> evenList) {
		super();
		this.oddList = oddList;
		this.evenList = evenList;
	}



	public void run() {
		Integer odd = 0;
		Integer even = 0;
		
		NumberGenLockService.getInstance().getLock().lock();
		try {
			while (true) {
				if (oddList.isEmpty()) {
					try {
						//System.out.println(" ---- odd empty ----");
						NumberGenLockService.getInstance().getNotFull().await();
						//System.out.println(" ---- odd get signal ----");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				odd = oddList.poll();
				//System.out.println(Thread.currentThread().getName() + " recvd odd = " + odd);
				
				if (evenList.isEmpty()) {
					try {
						//System.out.println(" ---- even empty ----");
						NumberGenLockService.getInstance().getNotFull().await();
						//System.out.println(" ---- even get signal ----");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
				}
				even = evenList.poll();
				//System.out.println(Thread.currentThread().getName() + " recvd even = " + even);
				
				System.out.println(odd);
				System.out.println(even);
				if ((even + odd) % 5 == 0) {					
					System.out.println("found!" + (odd + even));					
				}
				System.out.println("-------------");
			}
		} finally {
			NumberGenLockService.getInstance().getLock().unlock();
		}
		
	}

}
