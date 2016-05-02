package com.chaturv.datastructures.trees.misc;


import com.chaturv.datastructures.Node;

public class MyQueue {
	
	private Node head;
	
	public void enqueue(String value) {
//		if (head == null) {
//			head = new Node(value);
//		} else {
//			Node current = head;
//			Node next = current.next;
//			while (next != null) {
//				current = next;
//				next = next.next;
//			}
//			next = new Node(value);
//			current.next = next;
//		}
	}
	
	
	public String dequeue() {
//		Node current = head;
//		if (current != null) {
//			head = current.next;
//			return current.data;
//		}
	return null;
	}
	
	public void print() {
//		Node current = head;
//		while (current != null) {
//			System.out.println(current.data);
//			current = current.next;
//		}
	}


	public boolean isEmpty() {
		return head == null;
	}


	public int size() {
//		int size = 0;
//		Node current = head;
//		while (current != null) {
//			size++;
//			current = current.next;
//		}
//		for (int i = 0; i < size; i++) {
//			System.out.print("#");
//		}
//		System.out.print(" -- " + size + "\n");
//		return size;
        return 0;
	}
}