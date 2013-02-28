/**
 * 
 */
package tim.game.ai;

import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.back.Road;
import tim.data.unit.Builder;
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
	public RoadJob(Unit unit, Path path) {
		this.unit = unit;
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		System.out.println("road build");
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
