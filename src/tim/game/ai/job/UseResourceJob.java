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
	
	String resource;
	int amount;

	/**
	 * 
	 */
	public UseResourceJob(Unit unit, String resource, int amount) {
		this.unit = unit;
		this.resource = resource;
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
		Worker w = ((Worker)unit);
		w.giveResource(resource, amount);
		finished = true;
	}

}
