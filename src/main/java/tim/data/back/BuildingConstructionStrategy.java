/**
 * 
 */
package tim.data.back;

import java.util.EnumMap;

import tim.game.ai.ResourcesData;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * Strategy for buildings that are under construction
 */
public class BuildingConstructionStrategy implements BuildingStrategy {
	
	Building building;
	ResourceInfo info;
	
	EnumMap<Resource, Integer> requiredResources;
	
	
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
		}
		
	}

}
