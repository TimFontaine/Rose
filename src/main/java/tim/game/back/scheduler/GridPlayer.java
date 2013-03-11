/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tim.data.back.Building;
import tim.data.back.Event;
import tim.data.back.HQ;
import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.unit.UnitOrder;
import tim.game.Back;
import tim.game.Player;
import tim.game.ai.BasicPlayer;
import tim.game.factory.GameApplicationFactory;
import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public class GridPlayer extends BasicPlayer implements Player {
	
	PlayerScheduler scheduler;
	List<Grid> grids;
	
	/**
	 * 
	 */
	public GridPlayer() {
		scheduler = new SimplePlayerScheduler();
		grids = new ArrayList<Grid>();
		scheduler.setGrids(grids);
		scheduler.setUnits(units);
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
		grid.setCenter(gridPoint);
		grid.setNodes(nodes);
		
		//add a hq to a grid
		Building building = new HQ("base");
		building.setX(gridPoint.x);
		building.setY(gridPoint.y);
		applicationFactory.getBack().addBuilding(building);
		grid.setBase(building);
		grids.add(grid);
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#doLogic()
	 */
	public void doLogic() {
		//assign units to grid
		scheduler.doAction();
		//assign grid orders to units
		for (Grid grid : grids) {
			grid.doAction();
		}
		
		for (Unit unit: units) {
			unit.doLogic();
		}
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addEvent(tim.data.back.Event)
	 */
	public void addEvent(Event event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#initTurn()
	 */
	public void initTurn() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUsedItem(tim.data.back.Item)
	 */
	public void addUsedItem(Item item) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#orderFinished(tim.data.unit.Unit, tim.data.unit.UnitOrder)
	 */
	public void orderFinished(tim.data.unit.Unit source, UnitOrder order) {
		// TODO Auto-generated method stub
		
	}

}
