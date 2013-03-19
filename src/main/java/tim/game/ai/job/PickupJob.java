/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.building.Building;
import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.data.unit.Worker;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class PickupJob extends Job {
	
	private int amount;
	
	private Resource resource;
	
	/**
	 * 
	 */
	public PickupJob(Unit unit, int destinationX, int destinationY, Resource resource, int amount) {
		init(resource, amount);
//		path = back.findShortestPath(unit.getX(), unit.getY(), destinationX, destinationY);
	}
	
	public PickupJob(Unit unit, Resource resource, int amount) {
		this.unit = unit;
		init(resource, amount);
//		path = back.findNearestObject(unit, itemName);
	}
	
	
	@Override
	public void start() {
	}
	
	
	private void init(Resource resource, int amount) {
		this.resource = resource;
		this.amount = amount;
	}
	

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		Node current = back.getNode(unit.getLocation().x, unit.getLocation().y);
		//worker? receives from mine?
		//calc the max amount to pick up 
//		int availableSpace = unit.getFreeStorage();
//		if (availableSpace < amount) {
//			//not enough space available to store the requested
//			amount = availableSpace;
//		}
		System.out.println("pickupjob");
		unit.addResource(resource, amount);
		back.addUsedItem(current.getItem());
		finished = true;

	}
	
	
	


}
