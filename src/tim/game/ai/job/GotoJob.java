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
		super(unit);
		this.destination = destination;
	}
	
	public GotoJob(Unit unit, String mapItemType) {
		super(unit);
		this.mapItemType = mapItemType;
	}
	
	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		if (unit.getLocation().equals(destination)) {
			//we are on the destination
			finished = true;
		}
		if (destination == null && mapItemType == null) {
			System.out.println("error: gotojob no destination or mapitemtype to goto set");
		}
		if (mapItemType != null) {
			path = back.findNearestObject(unit, mapItemType);
		} else if (destination != null) {
			path = back.findShortestPath(unit.getX(), unit.getY(), destination.x, destination.y);
			
		}
	}
	
	public void doAction() {
		move();
		//test on destination
		if (testOnDestination()) {
			onDestination();
		}
	}

	

	

}
