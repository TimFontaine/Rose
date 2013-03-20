/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.MoveJob;
import tim.game.usercentric.Actor;

/**
 * @author tfontaine
 *
 */
public class GotoJob extends MoveJob {
	
	protected Point destination;
	String mapItemType;
	
	private int step;
	
	/**
	 * @param node 
	 * 
	 */
	public GotoJob(Unit unit, Point destination) {
		super(unit);
		this.destination = destination;
		start();
	}
	
	public GotoJob(Unit unit, String mapItemType) {
		super(unit);
		this.mapItemType = mapItemType;
	}
	
	/**
	 * @param destination2
	 */
	public GotoJob(Point destination) {
		super(null);
		this.destination = destination;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		if (unit.getLocation().equals(destination)) {
			//we are on the destination
			finished = true;
			return;
		}
		if (destination == null && mapItemType == null) {
			System.out.println("error: gotojob no destination or mapitemtype to goto set");
		}
		if (mapItemType != null) {
			path = back.findNearestObject(unit, mapItemType);
		} else if (destination != null) {
			int x = unit.getLocation().x;
			int y = unit.getLocation().y;
			path = back.findShortestPath(x, y, destination.x, destination.y);
		}
	}
	
	public Point getNext() {
		Node nextNode = path.getPathNodes().get(step);
		step++;
		if (!path.hasNext(step)) {
			finished = true;
		}
		return nextNode.getLocation();
		
	}
	
	public void doAction() {
		move();
		//test on destination
		if (testOnDestination()) {
			onDestination();
		}
	}

	

	

}
