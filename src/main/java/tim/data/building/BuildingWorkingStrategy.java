/**
 * 
 */
package tim.data.building;

import java.util.EnumMap;
import java.util.Map;

import tim.data.back.BuildingState;
import tim.data.back.BuildingStateContext;
import tim.data.back.BuildingStrategy;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.ResourceContainer;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;
import tim.game.usercentric.Actor;
import tim.game.usercentric.WorkerActor;

/**
 * @author tfontaine
 *
 */
public class BuildingWorkingStrategy implements BuildingStrategy {
	
	Back back;
	BuildingData buildingData;
	ResourceInfo resourceInfo;

	/**
	 * 
	 */
	public BuildingWorkingStrategy(BuildingData buildingData) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		back = applicationFactory.getBack();
		this.buildingData = buildingData;
		buildingData.setImageName("factory");
		buildingData.setState(BuildingState.IDLE);
	}

	/* (non-Javadoc)
	 * @see tim.data.back.BuildingStrategy#doAction()
	 */
	@Override
	public void doAction(BuildingStateContext context) {
		if (buildingData.getOrderName() == null) {
			return;
		}
		String orderName = buildingData.getOrderName();
		EnumMap<Resource, Integer> requiredResources = resourceInfo.getResourcesForThing(orderName);
		boolean test = testCanBuild(requiredResources);
		if (test) {
			buildUnit(orderName);
			useResources(requiredResources);
			buildingData.setOrderName(null);
		} else {
		}
	}
	
	/**
	 * 
	 */
	private void useResources(EnumMap<Resource, Integer> requiredResources) {
		buildingData.getResourceContainer().retreiveMultipleResources(requiredResources);
	}

	private void buildUnit(String orderName) {
		Unit unit = (Unit) RoseObjectFactory.getInstance().getRoseObject(orderName);
		unit.setLocation(buildingData.getLocation());
		((Actor)unit).getData().setLocation(buildingData.getLocation());
		back.addUnit(unit);
		
		System.out.println("factory has build unit");
//		producedUnits++;
	}

	private boolean testCanBuild(EnumMap<Resource, Integer> requiredResources) {
		ResourceContainer resourceContainer = buildingData.getResourceContainer();
		for (Map.Entry<Resource, Integer> entry : requiredResources.entrySet()) {
			if (!resourceContainer.containsResource(entry.getKey())) {
				return false;
			}
			int cost = entry.getValue();
			int available = resourceContainer.getAvailableResources(entry.getKey());
			int rest = cost - available;
			if (rest >0) {
				return false;
			}
			
		}
		return true;
	}

}
