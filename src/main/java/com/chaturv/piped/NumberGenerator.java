package com.chaturv.piped;

import java.util.LinkedList;

public class NumberGenerator implements Runnable {

	private LinkedList<Integer> list;
	private int maxNumber;
	private boolean isEvenNumber;



	public NumberGenerator(LinkedList<Integer> list, int maxNumber, boolean isEvenNumber) {
		super();
		this.list = list;
		this.maxNumber = maxNumber;
		this.isEvenNumber = isEvenNumber;
	}



	@Override
	public void run() {
		int i = 1;		
		try {
			while (i <= maxNumber) {				
				try {
					//System.out.println(Thread.currentThread().getName() + " try lock");
					NumberGenLockService.getInstance().getLock().lock();
					//System.out.println(Thread.currentThread().getName() + " got lock");
					
					if (isEvenNumber && (i % 2) == 0) {
						
						list.add(i);
						//System.out.println(Thread.currentThread().getName() + " added " + i + " to list");
						//System.out.println(Thread.currentThread().getName() + " try signal");					
						NumberGenLockService.getInstance().getNotFull().signal();
						//System.out.println(Thread.currentThread().getName() + " did signal");
					} else if (!isEvenNumber && i%2 != 0) {

						list.add(i);
						//System.out.println(Thread.currentThread().getName() + " added " + i + " to list");
						//System.out.println(Thread.currentThread().getName() + " try signal");					
						NumberGenLockService.getInstance().getNotFull().signal();
						//System.out.println(Thread.currentThread().getName() + " did signal");
					}
					++i;
					
				} finally {
					//System.out.println(Thread.currentThread().getName() + " try unlock");
					NumberGenLockService.getInstance().getLock().unlock();
					//System.out.println(Thread.currentThread().getName() + " did unlock");
				}

			}
		} finally {
			
		}

	}
}
