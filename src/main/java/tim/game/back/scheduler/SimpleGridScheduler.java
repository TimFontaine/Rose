/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import tim.data.unit.Unit;
import tim.data.unit.UnitState;

/**
 * @author tim
 *
 */
public class SimpleGridScheduler implements GridScheduler {

	/**
	 * 
	 */
	public SimpleGridScheduler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridScheduler#doAction()
	 */
	@Override
	public void doAction(List<Unit> units, List<Order> orderList) {
		Queue<Order> orderQueue = new PriorityQueue<Order>();
		orderQueue.addAll(orderList);
		for (Unit unit : units) {
			if (unit.getState() == UnitState.IDLE) {
				unit.giveOrder(orderQueue.poll());
			}
		}
	}

}
