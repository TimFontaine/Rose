/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import tim.data.unit.DummyUnit;
import tim.data.unit.Unit;
import tim.data.unit.Worker;
import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class UnitTest {
	
	Unit unit;
	
	
	@Before
	public void setup() {
		unit = new DummyUnit("dummy");
		unit.setLocation(new Point(2,2));
	}
	
	@Test
	public void giveOrder() {
		Order order = new Order();
		order.setDestination(new Point(5,5));
		order.setAction(OrderAction.BUILD);
		unit.giveOrder(order);
		assertEquals(order, unit.getOrder());
	}

}
