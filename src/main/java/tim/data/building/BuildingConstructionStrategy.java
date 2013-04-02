/**
 * 
 */
package tim.data.building;

import java.util.EnumMap;
import java.util.Map;

import tim.data.back.BuildingStateContext;
import tim.data.back.BuildingStrategy;
import tim.data.back.Node;
import tim.game.Back;
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
	Back back;	
	EnumMap<Resource, Integer> requiredResources;
	
	
	/**
	 * 
	 */
	public BuildingConstructionStrategy(BuildingData buildingData, BuildingStateContext context) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		info = applicationFactory.getResourceInfo();
		buildingData.setImageName("underconstruction");
		this.buildingData = buildingData;
		String type = buildingData.getType();
		requiredResources = info.getResourcesForThing(type);
		
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
	}
	
	@Override
	public void doAction(BuildingStateContext context) {
		//are there enough resources to finish construction ?
		
		boolean test = buildingData.getResourceContainer().hasResourcesAvailable(requiredResources);
		if (test) {
			context.setState(new BuildingWorkingStrategy(buildingData));
		} else {
		}
		
	}

}
