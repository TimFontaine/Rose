/**
 * 
 */
package tim.game.ai;

import java.awt.Point;

import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.back.Road;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;
import tim.game.ai.job.Job;

/**
 * @author tfontaine
 *
 */
public class RoadJob extends Job {
	
	Unit unit;
	Path path;
	int step;

	/**
	 * @param path 
	 * @param builder 
	 * 
	 */
	public RoadJob(Unit unit) {
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		Node node = back.getNode(unit.getX(), unit.getY());
		if (node.containsRoad()) {
			finished = true;
		}
		Road road = new Road();
		back.buildOnTile(unit.getLocation().x, unit.getLocation().y, road);
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#start()
	 */
	@Override
	public void start() {
	}

}
