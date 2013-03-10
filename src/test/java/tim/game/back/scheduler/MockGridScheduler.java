/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public class MockGridScheduler implements GridScheduler {

	/**
	 * 
	 */
	public MockGridScheduler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridScheduler#doAction(java.util.List, java.util.List)
	 */
	@Override
	public void doAction(List<Unit> units, List<Order> orderList) {
		Queue<Order> orderQueue = new PriorityQueue<Order>();
		orderQueue.addAll(orderList);
		for (Unit unit : units) {
			unit.giveOrder(orderQueue.poll());
		}
	}

}
