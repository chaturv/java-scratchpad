package com.chaturv.locks;

public class Deadlocks {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		LockService lockService = new LockService();
		
		Thread ta = new Thread(new WorkerA(lockService));
		Thread tb = new Thread(new WorkerB(lockService));
		
		ta.start();		
		tb.start();
		
		
		
		ta.join();
		tb.join();
		
	}
	
	
	
	

}

class WorkerA implements Runnable {

	LockService  lockService;
	
	
	public WorkerA(LockService lockService) {
		super();
		this.lockService = lockService;
	}


	@Override
	public void run() {
		lockService.getLock1().lock();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		lockService.getLock2().lock();
		
		System.out.println("A done!");
		
		lockService.getLock2().unlock();
		lockService.getLock1().unlock();
		
	}
	
}

class WorkerB implements Runnable {

	LockService  lockService;
	
	public WorkerB(LockService lockService) {
		super();
		this.lockService = lockService;
	}

	@Override
	public void run() {
		lockService.getLock2().lock();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lockService.getLock1().lock();
		
		System.out.println("B done!");
		
		lockService.getLock1().unlock();
		lockService.getLock2().unlock();		
	}
	
}