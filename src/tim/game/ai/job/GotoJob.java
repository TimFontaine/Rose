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
	
	protected Point destination2;
	String mapItemType;
	
	/**
	 * @param node 
	 * 
	 */
	public GotoJob(Unit unit, Point destination) {
		if (unit.getName().equals("worker2")) {
			System.out.println("worker2 shortest path to:" + destination.x + ":" + destination.y);
		}
		this.destination2 = destination;
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
		if (unit.getName().equals("worker2")) {
			System.out.println("worker2 shortest path to:" + destination2.x + ":" + destination2.y);
		}
		if (destination2 == null && mapItemType == null) {
			System.out.println("error: gotojob no destination or mapitemtype to goto set");
		}
		if (mapItemType != null) {
			path = back.findNearestObject(unit, mapItemType);
		} else if (destination2 != null) {
			if (unit.getName().equals("worker2")) {
				System.out.println("worker2 shortest path to:" + destination2.x + ":" + destination2.y);
				System.out.println("worker2 result path:" +  path.getLast().getX() + ":" + path.getLast().getY());
			}
			path = back.findShortestPath(unit.getX(), unit.getY(), destination2.x, destination2.y);
			
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
			if (unit.getName().equals("worker2")) {
				int k = 15;
				System.out.println("gotojob for worker 2 is finished");
				System.out.println("worker 2 is on location:" + unit.getX() + ":" + unit.getY());
			}
			finished = true;
		}
		step++;
	}

	

	

}
