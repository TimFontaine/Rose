/**
 * 
 */
package tim.data.building;

import java.util.EnumMap;

import tim.data.back.BuildingState;
import tim.data.back.BuildingStateContext;
import tim.data.back.BuildingStrategy;
import tim.game.ai.ResourcesData;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * Strategy for buildings that are under construction
 */
public class BuildingConstructionStrategy implements BuildingStrategy {
	
	BuildingData buildingData;
	ResourceInfo info;
	
	EnumMap<Resource, Integer> requiredResources;
	
	
	/**
	 * 
	 */
	public BuildingConstructionStrategy(BuildingData buildingData, BuildingStateContext context) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		info = applicationFactory.getResourceInfo();
		buildingData.setImageName("underconstruction");
		
		String type = buildingData.getType();
		requiredResources = info.getResourcesForThing(type);
	}
	
	@Override
	public void doAction(BuildingStateContext context) {
		//are there enough resources to finish construction ?
		
		boolean test = buildingData.getResourceContainer().hasResourcesAvailable(requiredResources);
		if (test) {
			context.switchState(BuildingState.IDLE);
		} else {
		}
		
	}

}
