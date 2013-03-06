/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Building;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.data.unit.Worker;

/**
 * @author tfontaine
 *
 */
public class PickupJob extends Job {
	
	private int resourceKey;
	private int amount;
	
	/**
	 * 
	 */
	public PickupJob(Unit unit, int destinationX, int destinationY, int resourceKey, int amount) {
		init(resourceKey, amount);
//		path = back.findShortestPath(unit.getX(), unit.getY(), destinationX, destinationY);
	}
	
	public PickupJob(Unit unit, int resourceKey, int amount) {
		this.unit = unit;
		init(resourceKey, amount);
//		path = back.findNearestObject(unit, itemName);
	}
	
	
	@Override
	public void start() {
	}
	
	
	private void init(int resourceKey, int amount) {
		this.resourceKey = resourceKey;
		this.amount = amount;
	}
	

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		Node current = back.getNode(unit.getLocation().x, unit.getLocation().y);
		//worker? receives from mine?
		System.out.println("pickup up job " + unit.getName());
		unit.addResource(resourceKey, amount);
		back.addUsedItem(current.getItem());
		finished = true;

	}
	
	
	


}
