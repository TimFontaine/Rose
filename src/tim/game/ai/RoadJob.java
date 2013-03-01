/**
 * 
 */
package tim.game.ai;

import java.awt.Point;

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
	Point endLocation;

	/**
	 * @param path 
	 * @param builder 
	 * 
	 */
	public RoadJob(Unit unit, Point locationEnd) {
		this.unit = unit;
		this.endLocation = locationEnd;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#doAction()
	 */
	@Override
	public void doAction() {
		Road road = new Road();
		back.buildOnTile(unit.getLocation().x, unit.getLocation().y, road);
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#start()
	 */
	@Override
	public void start() {
		path = back.findShortestPath(unit.getX(), unit.getY(), endLocation.x, endLocation.y);
	}

}
