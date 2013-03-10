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
	
	int[] requiredResources;
	
	
	/**
	 * 
	 */
	public BuildingConstructionStrategy(Building building, BuildingStateContext context) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		info = applicationFactory.getResourceInfo();
		this.building = building;
		building.setImageName("underconstruction");
		
		String type = building.getType();
		requiredResources = info.getResourcesForThing(type);
	}
	
	@Override
	public void doAction(BuildingStateContext context) {
		//are there enough resources to finish construction ?
		
		boolean test = building.hasResourcesAvailable(requiredResources);
		if (test) {
			context.switchState(BuildingState.IDLE);
		} else {
//			//update the required resources
			int[] requirement = new int[requiredResources.length];
			for (int key = 0; key < requiredResources.length; key++) {
				int required = requiredResources[key] - building.getAvailableResources(key);
				if (required > 0) {
					requiredResources[key] = required;
				}
			}
//			building.setRequiredResources(requiredResources);
		}
		
	}

}
