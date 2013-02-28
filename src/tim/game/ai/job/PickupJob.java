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
	
	private TransferResource transferer;
	private String resourceName;
	private int amount;
	
	/**
	 * 
	 */
	public PickupJob(TransferResource transferer, int destinationX, int destinationY, String resourceName, int amount) {
		init(resourceName, amount);
//		path = back.findShortestPath(unit.getX(), unit.getY(), destinationX, destinationY);
	}
	
	public PickupJob(TransferResource transferer, String resourceName, int amount) {
		this.transferer = transferer;
		init(resourceName, amount);
//		path = back.findNearestObject(unit, itemName);
	}
	
	
	@Override
	public void start() {
	}
	
	
	private void init(String resourceName, int amount) {
		this.resourceName = resourceName;
		this.amount = amount;
	}
	

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		Node current = back.getNode(transferer.getLocation().x, transferer.getLocation().y);
		TransferResource unit = (TransferResource) current.getItem();
		if (unit == null || !(unit instanceof TransferResource)) {
			System.out.println("error: pickupjob is on location with no TransferResource object");
		}
		//worker? receives from mine?
		transferer.receiveResource(resourceName, amount);
		back.addUsedItem(current.getItem());
		finished = true;

	}
	
	
	


}
