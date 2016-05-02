package com.chaturv.misc;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOverriding {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyInterface my = new MyClass();
		try {
			my.foo();
		} catch (NullPointerException e) {
			System.out.println("here...");
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.out.println("rather here...");
			e.printStackTrace();
		}

	}

}

interface MyInterface {
	
	void foo() throws NullPointerException;
	
}

class MyClass implements MyInterface {

	@Override
	public void foo() throws RuntimeException {
		throw new RuntimeException();
		
	}
	
}
