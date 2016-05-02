package com.chaturv.datastructures.trees.misc;

public class TestMyQueue {
	
	public static void main(String[] args) {
		MyQueue myQueue = new MyQueue();
		myQueue.enqueue("1");
		myQueue.enqueue("2");
		myQueue.enqueue("3");
		myQueue.enqueue("4");
		myQueue.enqueue("5");
		
		myQueue.print();
		
		System.out.println(myQueue.dequeue());
		System.out.println(myQueue.dequeue());
		System.out.println(myQueue.dequeue());
		System.out.println(myQueue.dequeue());
		System.out.println(myQueue.dequeue());
		System.out.println(myQueue.dequeue());		
		
	}

}
