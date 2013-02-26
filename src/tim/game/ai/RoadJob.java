/**
 * 
 */
package tim.game.ai;

import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Builder;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;

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
			Node current = back.getMap().getNode(unit.getX(), unit.getY());
			//check tiles contains road
			if (current.getItem() != null && current.getItem().getName().equals("road")) {
				
				//check that there is a next step
				if (path.hasNext(step)) {
					back.moveUnit(unit, path.getPathNodes().get(step).getX(), path.getPathNodes().get(step).getY());
				} else {
					//destination reached
					finished = true;
					unit.setState(UnitState.IDLE);
				}
				
			} else {
				//build road on tile
				Item item = new Item("road");
				item.setImageName("road");
				item.setType("road");
				System.out.println("road build on tile");
				back.buildOnTile(unit.getX(), unit.getY(), item);
			}
	}

}
