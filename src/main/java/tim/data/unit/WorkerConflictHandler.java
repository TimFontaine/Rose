/**
 * 
 */
package tim.data.unit;

import tim.data.back.Node;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class WorkerConflictHandler implements ConflictHandler {
	
	Unit unit;

	/**
	 * 
	 */
	public WorkerConflictHandler(Unit unit) {
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.ConflictHandler#checkConflict(tim.data.back.Node)
	 */
	@Override
	public void checkConflict(Node node) {
		if (node.containsResource()) {
			//do we need the resource?
			Resource resource = node.getResource();
			unit.addResource(resource, 10);
			System.out.println("resource filled");
		}

	}

}
