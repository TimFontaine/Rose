/**
 * 
 */
package tim.game.ai;


import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.job.Job;
import tim.game.usercentric.Actor;
import tim.game.usercentric.WorkerActor;

/**
 * @author tfontaine
 *
 */
public abstract class MoveJob extends Job {

	protected Path path;
	protected int step;
	protected Actor unit;
	
	/**
	 * 
	 */
	public MoveJob(Actor unit) {
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
		unit.move(nextNode.getX(), nextNode.getY());
//		back.moveUnit(unit, nextNode.getX(), nextNode.getY());
		step++;
	}

}
