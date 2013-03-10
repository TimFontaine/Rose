/**
 * 
 */
package tim.game.back.scheduler;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub

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
