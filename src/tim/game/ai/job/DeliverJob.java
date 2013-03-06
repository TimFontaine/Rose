/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Building;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.data.unit.TransferResource;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class DeliverJob extends Job {
	
	private int resourceKey;
	private int amount;

	/**
	 * 
	 */
	public DeliverJob(Unit unit, int resourceKey, int amount) {
		this.unit = unit;
		init(resourceKey, amount);
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
		/**
		 * TODO
		 * delivery is possible on unit and building
		 */
		Thing target = (Thing)current.getItem();
		//worker? delivers to factory?
		unit.retreiveResource(resourceKey, amount);
		target.addResource(resourceKey, amount);
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
