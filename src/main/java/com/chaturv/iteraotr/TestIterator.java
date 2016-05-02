package com.chaturv.iteraotr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestIterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 7;
		new TestIterator().testint(i);
		System.out.println(i);
		
		String s = "Hello";
		String s1 = "Hello";
		String s2 = new String("Hello");
		
		System.out.println(s == s1);
		

	}
	
	public void foo() {
		List<String> l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
		
		ListIterator<String> iterator = l.listIterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.add("0");
			break;
		}
		System.out.println(l);
	}
	
	public void randomize(int[] vals) {
		Collections.shuffle(Arrays.asList(vals));
	}

	public void testint(int i) {
		i = 5;
		System.out.println(i);
	}

}
