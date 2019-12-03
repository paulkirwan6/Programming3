import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Paul kirwan
 * 17321313
 */

// Class extends Thread and it simulates the preparation of orders
public class Chef extends Thread {

	private ReentrantLock re;
	private String name;
	private int total, bCount, pCount, fCount;
	private Queue<String> orders = new LinkedList<String>();
	private Queue<String> prepared = new LinkedList<String>();
	private String order;
	private Condition preparedReady;
	private Condition moreOrders;

	public Chef(ReentrantLock rl, String name, Queue<String> orders, Queue<String> prepared, Condition preparedReady, Condition moreOrders) {
		re = rl;
		this.name = name;
		this.orders = orders;
		this.prepared = prepared;
		this.preparedReady = preparedReady;
		this.moreOrders = moreOrders;
	}

	@Override
	public void run() {
		while (!orders.isEmpty()) {
			
			//sleep so there is an offset between chef and server attempting to lock
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Lock & return true or false
			boolean free = re.tryLock();
			if (free) {
				try {
					
					//sleep for a random time between 0 & 200ms (longer than server's delay)
					sleep((long) (Math.random()*200)); 

					//Remove order from the Chef's Queue and add to the Server's Queue
					order = orders.poll();

					//Take order from queue based on FIFO
					prepared.add(order);

					//signal that prepared queue is not empty
					preparedReady.signal();

					//Count the different order types
					total += 1;
					if (order.toLowerCase().contains("pizza"))
						pCount += 1;
					else if (order.toLowerCase().contains("burger"))
						bCount += 1;
					else
						fCount += 1;

					System.out.println("Chef "+ name + " is preparing " + order);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Always unlock
				finally {
					re.unlock();
				}
			}
		}
	}

	//Print no. of orders prepared by this Chef
	@Override
	public String toString() {
		return "Chef " + name + " finished preparing "+ total + " orders including "
				+ bCount + " burgers, " + pCount + " pizzas and " + fCount + " fish n chips";
	}
}
