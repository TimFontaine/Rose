/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;


/**
 * @author tim
 *
 */
public class SchedulerTest {
	
	Scheduler scheduler;
	
	@Before
	public void setup() {
		scheduler = new Scheduler();
		Unit unit = new Unit("worker1");
		scheduler.addUnit(unit);
		unit.setLocation(new Point(5, 5));
		
		Grid grid = new Grid();
		grid.setCenter(new Point(6,5));
		scheduler.addGrid(grid);
	}
	
	@Test
	public void doAction() {
		scheduler.doAction();
		Grid grid = scheduler.getGrids().get(0);
		assertEquals(1, grid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
	@Test 
	public void doActionmultiGrids() {
		Grid grid2 = new Grid();
		grid2.setCenter(new Point(20,20));
		scheduler.addGrid(grid2);
		
		scheduler.doAction();
		Grid grid = scheduler.getGrids().get(0);
		Grid otherGrid = scheduler.getGrids().get(1);
		assertEquals(1, grid.getAssignedUnits().size());
		assertEquals(0, otherGrid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
	@Test 
	public void doActionmultiGridsmultiUnit() {
		Grid grid2 = new Grid();
		grid2.setCenter(new Point(20,20));
		scheduler.addGrid(grid2);
		
		Unit unit2 = new Unit("worker2");
		unit2.setLocation(new Point(6,6));
		scheduler.addUnit(unit2);
		
		scheduler.doAction();
		Grid grid = scheduler.getGrids().get(0);
		Grid otherGrid = scheduler.getGrids().get(1);
		assertEquals(2, grid.getAssignedUnits().size());
		assertEquals(0, otherGrid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
}
