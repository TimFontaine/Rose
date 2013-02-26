/**
 * 
 */
package tim.game.ai;

import java.util.List;

import tim.data.back.Event;
import tim.data.back.EventCode;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;
import tim.pathfinding.AStar;

/**
 * @author tfontaine
 *
 */
public abstract class MoveJob extends Job {

	protected Path path;
	protected int step;
	protected Unit unit;
	protected Node destination;
	
	/**
	 * 
	 */
	public MoveJob() {
	}
	
	public void doAction() {
		if (path == null) {
			unit.setState(UnitState.IDLE);
		}
		if (!unit.canMove()) {
			return;
		}
		if (unit.getState() == UnitState.ACTIVE) {
			List<Node> list = path.getPathNodes();
			Node next = list.get(step);
			int result = back.moveUnit(unit, next.getX(), next.getY());
			
			if (result != EventCode.SUCCESS) {
				if (result == EventCode.UNIT_CANNOT_MOVE) {
				//oil is empty?
					return;
				} else if (result == EventCode.TILE_BLOCKED) {
					System.out.println("tile is block, searching for new path");
					//tile is blocked, find a new way
					path = back.findShortestPath(unit.getX(), unit.getY(), destination.getX(), destination.getY());
					step = 0;
					if (path != null) {
						doAction();
					}
					return;
					
				}
			} else {
				//move done, substract possible move for this turn
				unit.useOil();
				int moves = unit.getMoves() - Unit.MIN_MOVE_COST;
				unit.setMoves(moves - Unit.MIN_MOVE_COST);
				if (moves <= 0) {
					System.out.println("unit is out of moves");
					unit.setState(UnitState.TURN_FINISHED);
				}
				step++;
			}
			
			if (next == destination) {
				onDestination();
			}
		}	
	}
	
	protected abstract void onDestination();

}
