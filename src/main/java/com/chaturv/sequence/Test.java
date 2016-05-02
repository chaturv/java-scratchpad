package com.chaturv.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Integer.valueOf("11", 2));

	}
	
	public void foo(Collection<? extends Shape<T>> shapes) {
		
	}

}

class Shape<T> {
	
}


class Circle<T> extends Shape<T> {
	
}