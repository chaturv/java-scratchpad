package com.chaturv.piped;

import java.util.LinkedList;
import java.util.List;

public class TestNumberGenerator {
	
	public static void main(String[] args) throws InterruptedException {
		LinkedList<Integer> evens = new LinkedList<Integer>();
		LinkedList<Integer> odds = new LinkedList<Integer>();
		int maxNumber = 10;
		
		NumberGenerator evenGenerator  = new NumberGenerator(evens, maxNumber, true);
		NumberGenerator oddGenerator = new NumberGenerator(odds, maxNumber, false);
		
		NumberCompiler compiler = new NumberCompiler(odds, evens);
		
		Thread thread = new Thread(evenGenerator);
		thread.start();
		
		Thread thread2 = new Thread(oddGenerator);
		thread2.start();
		
		Thread thread3 = new Thread(compiler);
		thread3.start();
		
		thread.join();
		thread2.join();
		thread3.join();
	}

}
