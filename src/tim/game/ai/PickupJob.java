/**
 * 
 */
package tim.game.ai;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.data.unit.Worker;

/**
 * @author tfontaine
 *
 */
public class PickupJob extends Job {
	
	private Worker unit;
	private Path path;
	private String resourceName;
	private int amount;
	
	int step;

	/**
	 * 
	 */
	public PickupJob(Worker unit, Path path, String resourceName, int amount) {
		this.unit = unit;
		this.path = path;
		this.resourceName = resourceName;
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		//test on destination
		if (unit.getX() == path.getLast().getX() && unit.getY() == path.getLast().getY()) {
			//pickup resource;
			if ("iron".equals(resourceName)) {
				unit.setIron(unit.getIron() + amount);
			} else if ("oil".equals(resourceName)) {
				unit.setOil(unit.getOil() + amount);
			}
			this.finished = true;
			return;
		}
		Node nextNode = path.getPathNodes().get(step);
		back.moveUnit(unit, nextNode.getX(), nextNode.getY());
		step++;
	}

}
