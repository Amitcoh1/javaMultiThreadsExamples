package multiThreadPractice;

public class deadLockSolution
{
	public static Object Lock1 = new Object();
	public static Object Lock2 = new Object();
	
	public static void main(String args[])
	{
		Thread t1 = new ThreadDemo1();
		Thread t2 = new ThreadDemo2();
		
		t1.start();
		t2.start();
	}
	private static class ThreadDemo1 extends Thread
	{
		public void run()
		{
			synchronized (Lock1) 
			{
				System.out.println("Thread1 is holding LOCK1");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread1 is waiting for LOCK2");
				
				synchronized (Lock2) 
				{
					System.out.println("Thread1 is holding LOCK1 & LOCK2");
				}
			}
		}
	}
	private static class ThreadDemo2 extends Thread
	{
		public void run()
		{
			synchronized (Lock1) 
			{
				System.out.println("Thread2 is holding LOCK2");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread2 is waiting for LOCK1");
				
				synchronized (Lock2) 
				{
					System.out.println("Thread2 is holding LOCK1 & LOCK2");
				}
			}

		}
	}

}