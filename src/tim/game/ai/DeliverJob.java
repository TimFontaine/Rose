/**
 * 
 */
package tim.game.ai;

import java.awt.Point;

import tim.data.back.Building;
import tim.data.back.Node;
import tim.data.unit.TransferResource;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class DeliverJob extends Job {
	
	private TransferResource transferer;
	private String resourceName;
	private int amount;

	/**
	 * 
	 */
	public DeliverJob(TransferResource transferer, String resourceName, int amount) {
		this.transferer = transferer;
		init(resourceName, amount);
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
		TransferResource receiver = (TransferResource) current.getItem();
		if (receiver == null || !(receiver instanceof TransferResource)) {
			System.out.println("error: delivery is on location with no TransferResource object");
		}
		//worker? delivers to factory?
		receiver.receiveResource(resourceName, amount);
		finished = true;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	

}
