import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Paul kirwan
 * 17321313
 */

//  Class extends Thread and it simulates serving orders
public class Server extends Thread {

	private ReentrantLock re;
	private String name;
	private int total, bCount, pCount, fCount;
	private Queue<String> orders = new LinkedList<String>();
	private Queue<String> prepared = new LinkedList<String>();
	private String order;
	private Condition preparedReady;
	private Condition moreOrders;
	private Condition finished;

	public Server(ReentrantLock rl, String name, Queue<String> orders, Queue<String> prepared, Condition preparedReady, Condition moreOrders, Condition finished) {
		re = rl;
		this.name = name;
		this.orders = orders;
		this.prepared = prepared;
		this.preparedReady = preparedReady;
		this.moreOrders = moreOrders;
		this.finished = finished;
	}

	@Override
	public void run() {

		while (!orders.isEmpty() || !prepared.isEmpty()) {
			
			//Lock & return true or false
			boolean free = re.tryLock();
			if (free) {
				try {
					
					//Wait for more orders to be added to the prepared queue
					while (prepared.isEmpty()) {
						try {
							preparedReady.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					//sleep for a random time between 0 & 50ms (shorter than chef's delay)
					sleep((long) (Math.random()*50)); 
					
					//Take order from queue based on FIFO
					order = prepared.poll();

					//Count the different order types
					total += 1;
					if (order.toLowerCase().contains("pizza"))
						pCount += 1;
					else if (order.toLowerCase().contains("burger"))
						bCount += 1;
					else
						fCount += 1;

					System.out.println("Server "+ name + " is serving " + order);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				//Always unlock
				finally {
					re.unlock();
				}
			}
		}
		//Servers are finished
		try {
			re.lock();
			finished.signal();
		}
		finally {
			re.unlock();
		}
	}

	@Override
	public String toString() {
		return "Server " + name + " finished serving "+ total + " orders including "
				+ bCount + " burgers, " + pCount + " pizzas and " + fCount + " fish n chips";
	}
}
