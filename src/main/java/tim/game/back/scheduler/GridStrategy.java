/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;

/**
 * @author tim
 *
 */
public interface GridStrategy {

	public void doAction();
	
	public List<Order> getOrders();
}
