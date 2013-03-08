/**
 * 
 */
package tim.data.back;

import tim.game.ai.ResourcesData;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * Strategy for buildings that are under construction
 */
public class BuildingConstructionStrategy implements BuildingStrategy {
	
	Building building;
	ResourceInfo info;
	
	
	/**
	 * 
	 */
	public BuildingConstructionStrategy(Building building, BuildingStateContext context) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		info = applicationFactory.getResourceInfo();
		this.building = building;
		building.setImageName("underconstruction");
	}
	
	@Override
	public void doAction(BuildingStateContext context) {
		//are there enough resources to finish construction ?
		String type = building.getType();
		int[] requiredResources = info.getResourcesForThing(type);
		boolean test = building.hasResourcesAvailable(requiredResources);
		if (test) {
			context.switchState(BuildingState.IDLE);
		} else {
			//update the required resources
			int[] requirement = new int[requiredResources.length];
			for (int key = 0; key < requiredResources.length; key++) {
				int required = requiredResources[key] - building.getAvailableResources(key);
				if (required > 0) {
					requirement[key] = required;
				}
			}
			building.setRequiredResources(requiredResources);
		}
		
	}

}
