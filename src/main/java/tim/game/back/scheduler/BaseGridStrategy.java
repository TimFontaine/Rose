/**
 * 
 */
package tim.game.back.scheduler;

import java.util.ArrayList;
import java.util.List;

import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class BaseGridStrategy implements GridStrategy {
	
	private List<Order> orderList;

	/**
	 * 
	 */
	public BaseGridStrategy() {
		orderList = new ArrayList<Order>();
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#doAction()
	 */
	public void doAction() {
		Order order = new Order();
		order.setAction(OrderAction.BUILD);
		orderList.add(order);
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#getOrders()
	 */
	public List<Order> getOrders() {
		return orderList;
	}

}
