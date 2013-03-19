/**
 * 
 */
package tim.game.ai;


import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.job.Job;
import tim.game.usercentric.CentricWorker;

/**
 * @author tfontaine
 *
 */
public abstract class MoveJob extends Job {

	protected Path path;
	protected int step;
	protected Unit unit;
	
	/**
	 * 
	 */
	public MoveJob(Unit unit) {
		this.unit = unit;
	}
	
	public boolean testOnDestination()  {
		if (unit.getLocation().equals(path.getLast().getLocation())){
			return true;
		}
		return false;
	}
	
	protected void onDestination() {
		finished = true;
	}
	
	protected void move() {
		Node nextNode = path.getPathNodes().get(step);
		back.moveUnit((CentricWorker)unit, nextNode.getX(), nextNode.getY());
		step++;
	}

}
