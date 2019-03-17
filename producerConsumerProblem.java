package multiThreadPractice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class producerConsumerProblem {
	public static void main(String args[])
	{
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingDeque<Integer>();
		Producer p = new Producer(sharedQueue);
		Consumer c = new Consumer(sharedQueue);
		
		p.start();
		c.start();
	}
}
class Producer extends Thread
{
	private BlockingQueue<Integer> sharedQueue;
	
	public Producer(BlockingQueue<Integer> aQueue)
	{
		super("PRODUCER");
		this.sharedQueue = aQueue;
	}
	public void run()
	{
		for(int i=0;i<10;i++)
		{
			System.out.println(getName() + " produced: " + i);
			try {
				//add the integer to the queue
				sharedQueue.put(i);
				//this line of sleep gives the consumer time to consume the data
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread
{
	private BlockingQueue<Integer> sharedQueue;
	
	public Consumer(BlockingQueue<Integer> aQueue)
	{
		super("CONSUMER");
		this.sharedQueue = aQueue;
	}
	public void run()
	{
		while(true)
		{
			try {
				Integer item = sharedQueue.take();
				System.out.println(getName() + " consumed: " + item);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


