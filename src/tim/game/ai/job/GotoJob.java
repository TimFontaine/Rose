/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.MoveJob;

/**
 * @author tfontaine
 *
 */
public class GotoJob extends MoveJob {
	
	protected Point destination;
	String mapItemType;
	
	/**
	 * @param node 
	 * 
	 */
	public GotoJob(Unit unit, Point destination) {
		this.destination = destination;
		this.unit = unit;
	}
	
	public GotoJob(Unit unit, String mapItemType) {
		this.mapItemType = mapItemType;
		this.unit = unit;
	}
	
	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		if (destination == null && mapItemType == null) {
			System.out.println("error: gotojob no destination or mapitemtype to goto set");
		}
		if (mapItemType != null) {
			path = back.findNearestObject(unit, mapItemType);
		} else if (destination != null) {
			path = back.findShortestPath(unit.getX(), unit.getY(), destination.x, destination.y);
		}
	}

	@Override
	protected void onDestination() {
		finished = true;
	}
	
	public void doAction() {
		Node nextNode = path.getPathNodes().get(step);
		back.moveUnit(unit, nextNode.getX(), nextNode.getY());
		//test on destination
		if (!path.hasNext(step)) {
			finished = true;
		}
		step++;
	}

	

	

}
