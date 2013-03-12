/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Node;
import tim.game.Back;
import tim.game.factory.GameApplicationFactory;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;

/**
 * @author tim
 *
 */
public class SimplePlayerScheduler implements PlayerScheduler {
	
	private List<Grid> grids;
	private List<Unit> units;
	
	/**
	 * 
	 */
	public SimplePlayerScheduler() {
		grids = new ArrayList<Grid>();
		units = new ArrayList<Unit>();
//		setupGrids();
	}
	
	public void doAction(){
		defineGridPriorities();
		assignUnitsToGrids();
	}

	/**
	 * 
	 */
	private void assignUnitsToGrids() {
		for (Unit unit : units) {
			if (unit.getState() == UnitState.IDLE) {
				int bestMatch = -Integer.MIN_VALUE;
				Grid bestGrid = null;
				for (Grid grid : grids) {
					int match = computeMatchToGoal(grid, unit);
					if (match > bestMatch) {
						bestMatch = match;
						bestGrid = grid;
					}
				}
				if (bestGrid != null) {
					bestGrid.addUnit(unit);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void defineGridPriorities() {
		for (Grid grid : grids) {
			grid.definePriority();
		}
	}
	
	/**
	 * @param unit
	 */
	private int computeMatchToGoal(Grid grid, Unit unit) {
		int distanceFromGoal = distanceToGrid(grid, unit);
		System.out.println("distance from goal is" + distanceFromGoal);
		int match = grid.getPriority() / (distanceFromGoal + 1);//distance to goal can be zero
		return match;
	}
	
	private int distanceToGrid(Grid grid, Unit unit) {
		Point source = unit.getLocation();
		Point target = grid.getCenter();
		if (source==null) {
			System.out.println("source is null");
		}
		if (target==null) {
			System.out.println("target is null");
		}
		int distance = (int)source.distance(target);
		return distance;
	}
	
	public void addGrid(Grid grid) {
		grids.add(grid);
	}
	
	public void addUnit(Unit unit) {
		units.add(unit);
	}
	
	public List<Grid> getGrids() {
		return grids;
	}

	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
}
