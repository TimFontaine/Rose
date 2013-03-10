/**
 * 
 */
package tim.game.ai.job;

import java.util.Map;

import tim.data.unit.Unit;
import tim.data.unit.Worker;

/**
 * @author tim
 *
 */
public class UseResourceJob extends Job {
	
	int resourceKey;
	int amount;

	/**
	 * 
	 */
	public UseResourceJob(Unit unit, int resourceKey, int amount) {
		this.unit = unit;
		this.resourceKey = resourceKey;
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#doAction()
	 */
	@Override
	public void doAction() {
		unit.retreiveResource(resourceKey, amount);
		finished = true;
	}

}
