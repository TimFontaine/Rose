/**
 * 
 */
package tim.game.ai;

import java.awt.Point;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class GotoJob extends MoveJob {
	
	/**
	 * @param node 
	 * 
	 */
	public GotoJob(Unit unit, Path path) {
		this.path = path;
		this.destination = path.getLast();
		this.unit = unit;
	}

	@Override
	protected void onDestination() {
		finished = true;
		
	}

}
