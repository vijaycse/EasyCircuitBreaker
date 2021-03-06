package com.neverquitter.service.generic.client.task;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TaskTimeQueue {

	private Queue<Long> queue = new LinkedList<Long>();
	public static int CAPACITY = 100;

	/**
	 * Array-based implementation of the queue.
	 */

	public TaskTimeQueue() {
		for (int i = 0; i < CAPACITY; i++) {
			queue.add(new Long(0));
		}
	}

	/**
	 * Test if the queue is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * Insert a new item into the queue.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void add(long x) {
		queue.offer(x);
		queue.poll();

	}

	public Iterator<Long> iterator() {
		return queue.iterator();
	}
	
	public static void main(String[] args) {
		System.out.println("...starting.....");
		TaskTimeQueue test = new TaskTimeQueue();
		for (int i = 0; i < 200; i++) {
			test.add(i);
		}

		System.out.println("...finishing.....");

		Iterator<Long> itr = test.iterator();
		Object next = null;
		while (itr.hasNext()) {
			next = itr.next();
			System.out.println(next.toString());
		}

		System.out.println("...done.....");

	}

}