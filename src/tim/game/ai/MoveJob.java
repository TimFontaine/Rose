/**
 * 
 */
package tim.game.ai;


import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.job.Job;

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
		if (unit.getName().equals("worker2")) {
			System.out.println("path last:" + path.getLast().getLocation());
			System.out.println("path first:" + path.first().getLocation());
			System.out.println("unit post:" + unit.getLocation());
		}
		if (unit.getX() == 6 && unit.getY() == 8 && unit.getName().equals("worker2")) {
			int k = 14;
		}
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
		back.moveUnit(unit, nextNode.getX(), nextNode.getY());
		step++;
	}

}
