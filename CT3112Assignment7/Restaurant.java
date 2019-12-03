import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Paul kirwan
 * 17321313
 */

// Class for creating an order queue for the incoming orders, a prepared meal queue for the servers
// and the related Chef and Server threads
public class Restaurant {

	//Create queues for chefs and servers
	private static Queue<String> orderQ = new LinkedList<String>();
	private static Queue<String> preparedQ = new LinkedList<String>();

	public static void main(String[] args) {

		ReentrantLock rel = new ReentrantLock();

		//Checks that orders have moved to the preparedQ
		Condition preparedReady = rel.newCondition();
		Condition moreOrders = rel.newCondition();
		Condition finished = rel.newCondition();

		//Add each order from text file as a String to the Queue
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("orders.txt"));
			String line = reader.readLine();
			while (line != null) {
				orderQ.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Create instances of Chef
		Chef chef1 = new Chef(rel, "John", orderQ, preparedQ, preparedReady, moreOrders);
		Chef chef2 = new Chef(rel, "Mark", orderQ, preparedQ, preparedReady, moreOrders);
		
		//Create instances of Server
		Server server1 = new Server(rel, "Katie", orderQ, preparedQ, preparedReady, moreOrders, finished);
		Server server2 = new Server(rel, "Andrew", orderQ, preparedQ, preparedReady, moreOrders, finished);
		Server server3 = new Server(rel, "Emily", orderQ, preparedQ, preparedReady, moreOrders, finished);
		
		//Execute run method
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		pool.execute(chef1);
		pool.execute(chef2);
		
		pool.execute(server1);
		pool.execute(server2);
		pool.execute(server3);
		
		pool.shutdown();
		
		//Wait for servers to finish to print chefs and servers together
		try {
			rel.lock();
			finished.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		finally {
			rel.unlock();
		}
		
		//Print summary of chefs & servers
		System.out.println(chef1);
		System.out.println(chef2);
		System.out.println(server1);
		System.out.println(server2);
		System.out.println(server3);
	}
}