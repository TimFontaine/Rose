/**
 * 
 */
package tim.data.unit;

import tim.data.back.Node;
import tim.data.back.ResourceItem;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class WorkerMoveHandler implements MoveHandler {
	
	Unit unit;

	/**
	 * 
	 */
	public WorkerMoveHandler(Unit unit) {
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.MoveHandler#handleMove()
	 */
	@Override
	public void handleMove(Node node) {
		if (node.containsResource()) {
			ResourceItem resourceItem = node.getResource();
			Resource resource = resourceItem.getResource();
			int available = resourceItem.getStorage();
			unit.getResourceContainer().addResource(resource, available);
			resourceItem.update(-available);
		}
		
	}

}
