/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tim.data.unit.UnitState;
import tim.data.unit.Worker;
import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public class SimplePlayerSchedulerTest {
	
	PlayerScheduler scheduler;
	List<Unit> units;
	List<Grid> grids;
	
	@Before
	public void setup() {
		units = new ArrayList<Unit>();
		scheduler = new SimplePlayerScheduler();
		Unit unit = new Worker("worker1");
		units.add(unit);
		scheduler.setUnits(units);
		unit.setLocation(new Point(5, 5));
		
		grids = new ArrayList<Grid>();
		Grid grid = new Grid();
		grid.setCenter(new Point(6,5));
		grids.add(grid);
		scheduler.setGrids(grids);
	}
	
	@Test
	public void doAction() {
		scheduler.doAction();
		Grid grid = grids.get(0);
		assertEquals(1, grid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
	@Test 
	public void doActionmultiGrids() {
		Grid grid2 = new Grid();
		grid2.setCenter(new Point(20,20));
		grids.add(grid2);
		
		scheduler.doAction();
		Grid grid = grids.get(0);
		Grid otherGrid = grids.get(1);
		assertEquals(1, grid.getAssignedUnits().size());
		assertEquals(0, otherGrid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
	@Test 
	public void doActionmultiGridsmultiUnit() {
		Grid grid2 = new Grid();
		grid2.setCenter(new Point(20,20));
		grids.add(grid2);
		
		Unit unit2 = new Worker("worker2");
		unit2.setLocation(new Point(6,6));
		units.add(unit2);
		
		scheduler.doAction();
		Grid grid = grids.get(0);
		Grid otherGrid = grids.get(1);
		assertEquals(2, grid.getAssignedUnits().size());
		assertEquals(0, otherGrid.getAssignedUnits().size());
		Unit worker = grid.getAssignedUnits().get(0);
		assertEquals("worker1", worker.getName());
	}
	
	@Test
	public void doActionWithActiveUnit() {
		Unit unit = units.get(0);
		unit.setState(UnitState.ACTIVE);
		
		scheduler.doAction();
		Grid grid = grids.get(0);
		List<Unit> assignedUnits = grid.getAssignedUnits();
		assertTrue(assignedUnits.isEmpty());
		
	}
}
