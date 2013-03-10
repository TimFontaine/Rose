/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;
import java.util.Queue;

import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public interface GridScheduler {
	
	/**
	 * @param units
	 * @param orderList
	 */
	void doAction(List<Unit> units, List<Order> orderList);
}
