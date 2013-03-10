/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class MockStrategy implements GridStrategy {
	
	private List<Order> orders;

	/**
	 * 
	 */
	public MockStrategy() {
		setOrders(new ArrayList<Order>());
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#doAction()
	 */
	public void doAction() {
		Order order = new Order();
		order.setAction(OrderAction.BUILD);
		order.setDestination(new Point(1, 1));
		orders.add(order);

	}
	

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#getOrders()
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
