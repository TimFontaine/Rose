/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class BaseGridStrategyTest {
	
	BaseGridStrategy gridStrategy;
	
	@Before
	public void setup() {
		gridStrategy = new BaseGridStrategy();
	}
	
	
	@Test
	public void doAction() {
		gridStrategy.doAction();
		assertFalse(gridStrategy.getOrders().isEmpty());
		List<Order> orders = gridStrategy.getOrders();
		Order order = orders.get(0);
		assertNotNull(order);
		assertEquals(OrderAction.BUILD, order.getAction());
		
		
	}

}
