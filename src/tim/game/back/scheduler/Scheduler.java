/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Node;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.back.scheduler.Grid.GridState;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tim
 *
 */
public class Scheduler {
	
	private List<Grid> grids;
	private List<Unit> units;
	
	/**
	 * 
	 */
	public Scheduler() {
		grids = new ArrayList<Grid>();
		units = new ArrayList<Unit>();
		setupGrids();
	}
	
	/**
	 * 
	 */
	private void setupGrids() {
		//define a grid for the base;
		Grid grid = new Grid();
		//assign nodes to a grid;
		Node[][] nodes = new Node[3][3];
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		//this is the upper left of the grid
		Point gridPoint = new Point(13,13);
		Back back = applicationFactory.getBack();
		//fill the grid nodes
		for (int i = 0; i <nodes.length; i++) {
			for (int j = 0; j< nodes[0].length; j++) {
				nodes[i][j] =  back.getNode(gridPoint.x + i, gridPoint.y +j);
			}
		}
		grid.setNodes(nodes);
		grid.setState(GridState.BASE);
		grids.add(grid);
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
		int match = distanceFromGoal * grid.getPriority();
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
