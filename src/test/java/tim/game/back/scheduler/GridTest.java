/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class GridTest {
	
	private List<Unit> assignUnits;
	private List<Order> orders;
	
	Grid grid;

	
	@Before
	public void setup() {
		assignUnits = new ArrayList<Unit>();
		orders = new ArrayList<Order>();
		grid = new Grid();
	}
	
	@Test
	public void doAction() {
		Unit unit = new Unit();
		
		Order order = new Order();
		order.setAction(OrderAction.BUILD);
		order.setPriority(10);
		
		Order order2 = new Order();
		order2.setAction(OrderAction.BUILD);
		order2.setPriority(20);
		
		List<Order> list = new ArrayList<Order>();
		list.add(order);
		list.add(order2);
		
		MockStrategy strategy = new MockStrategy();
		strategy.setOrders(list);
		
		grid.setStrategy(strategy);
		grid.addUnit(unit);
		grid.doAction();
		
		assertEquals(order2, unit.getOrder());
		
	}
	
}
