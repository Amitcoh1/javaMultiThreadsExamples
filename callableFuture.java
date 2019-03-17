package multiThreadPractice;
//imports
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class callableFuture {
	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random  random = new Random();
				int duration = random.nextInt(4000);
				if(duration > 2000)
					throw new IOException("sleeping for too long time .. ");
				
				 System.out.println("Starting..");
				Thread.sleep(duration);
				//do some work here
				System.out.println("Finished..");
				
				return duration;
				
			}
		});
		
		executorService.shutdown();
		System.out.println("duration of process is : " + future.get() );
	}
}
