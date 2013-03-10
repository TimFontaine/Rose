/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tim.data.unit.DummyUnit;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;

/**
 * @author tim
 *
 */
public class SimpleGridSchedulerTest {
	
	GridScheduler scheduler;
	List<Unit> units;
	List<Order> orders;

	@Before
	public void setup() {
		scheduler = new SimpleGridScheduler();
		units = new ArrayList<Unit>();
		orders = new  ArrayList<Order>();
	}
	
	@Test
	public void doAction() {
		Unit unit = new DummyUnit("dummy");
		units.add(unit);
		Order order = new Order();
		orders.add(order);
		scheduler.doAction(units, orders);
		assertEquals(order, unit.getOrder());
	}
	
	@Test
	public void doActionUnitStateIdle() {
		Unit unit = new DummyUnit("dummy");
		unit.setState(UnitState.IDLE);
		units.add(unit);
		Order order = new Order();
		orders.add(order);
		scheduler.doAction(units, orders);
		assertEquals(order, unit.getOrder());
	}
	
	@Test
	public void doActionUnitStateActive() {
		Unit unit = new DummyUnit("dummy");
		unit.setState(UnitState.ACTIVE);
		units.add(unit);
		Order order = new Order();
		orders.add(order);
		scheduler.doAction(units, orders);
		assertNull(unit.getOrder());
	}

}
