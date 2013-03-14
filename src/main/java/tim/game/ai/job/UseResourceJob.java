/**
 * 
 */
package tim.game.ai.job;

import java.util.Map;

import tim.data.unit.Unit;
import tim.data.unit.Worker;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tim
 *
 */
public class UseResourceJob extends Job {
	
	int resourceKey;
	int amount;
	Map<Resource,Integer> usageMap;
	
	/**
	 * 
	 */
	public UseResourceJob(Unit unit, int resourceKey, int amount) {
		this.unit = unit;
		this.resourceKey = resourceKey;
		this.amount = amount;
	}
	

	public UseResourceJob(Unit unit, Map<Resource, Integer> map) {
		this.unit = unit;
		usageMap = map;
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
		for (Map.Entry<Resource, Integer> entry : usageMap.entrySet()) {
			unit.retreiveResource(entry.getKey(), entry.getValue());
		}
	}

}
