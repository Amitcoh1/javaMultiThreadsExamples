package multiThreadPractice;

import java.awt.List;
import java.util.LinkedList;

public class blockingQueue {
	//define class items
	private LinkedList<Object> queue = new LinkedList<Object>();
	private int limit = 10;
	private int empty = 0;
	//constructor
	public blockingQueue(int limit,int empty)
	{
		this.limit = limit;
		this.empty = empty;
	}
	//insert items to the queue
	public synchronized void enqeue(Object item)
	{
		//the queue is full - you can't insert items
		while(this.queue.size() == this.limit)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//all threads please notice - the queue is empty you call insert data
		if(this.queue.size() == this.empty)
			notifyAll();
		//add an item
		this.queue.add(item);
	}
	//remove items from the queue
	public synchronized Object dequeue()
	{
		//nothing to remove .. release the lock 
		while(this.queue.size() == this.empty)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//all threads which are blocked - please notice that you can remove items
		if(this.queue.size() == this.limit)
			notifyAll();
		//remove an item 
		return this.queue.remove(0);
	}
}
