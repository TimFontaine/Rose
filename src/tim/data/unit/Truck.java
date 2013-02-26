/**
 * 
 */
package tim.data.unit;

import java.util.List;

import tim.data.back.Path;
import tim.game.ai.GotoJob;
import tim.game.ai.TransferJob;

/**
 * @author tfontaine
 *
 */
public class Truck extends Unit {

	/**
	 * 
	 */
	public Truck(String name) {
		super(name);
	}
	
	public void doLogic() {
		//go from mine to factory
		

		
		//execute job
		if (state == UnitState.ACTIVE) {
			job.doAction();
		}
		if (job.isFinished()) {
			job = new TransferJob(this, task);
		}
		
	}
	
	@Override
	public void initJob() {
		//find closest object
		if (job == null) {
			String destinationName = task.getNext();
			Path path = back.findNearestObject(this, destinationName);
			job = new GotoJob(this, path);
		}
	}

	@Override
	public List<Path> getUsedRoutes() {
		if (job instanceof TransferJob) {
		}
		return null;
	}

}
