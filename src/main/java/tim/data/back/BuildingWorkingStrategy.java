/**
 * 
 */
package tim.data.back;

import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class BuildingWorkingStrategy implements BuildingStrategy {
	
	Back back;
	Building building;
	ResourceInfo resourceInfo;

	/**
	 * 
	 */
	public BuildingWorkingStrategy(Building building) {
		GameApplicationFactory applicationFactory;
		applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		back = applicationFactory.getBack();
		this.building = building;
		building.setImageName("factory");
	}

	/* (non-Javadoc)
	 * @see tim.data.back.BuildingStrategy#doAction()
	 */
	@Override
	public void doAction(BuildingStateContext context) {
		int[] requiredResources = resourceInfo.getResourcesForThing("worker");
		boolean test = testCanBuild(requiredResources);
		if (test) {
			buildUnit(requiredResources);
		} else {
			
			
			
		}
	}
	
	private void buildUnit(int[] requiredResources) {
		Unit unit = (Unit) RoseObjectFactory.getInstance().getRoseObject("worker");
		unit.setName("worker");
		unit.setType("worker");
		unit.setOil(100);
		unit.setX(building.getX());
		unit.setY(building.getY());
		back.addUnit(unit);
		building.retreiveMultipleResources(requiredResources);
		System.out.println("factory has build unit");
//		producedUnits++;
	}

	
//	private void handleResourseCost() {
//		Map<String,Integer> required = requiredResources;
//		for (Map.Entry<String, Integer> resource : required.entrySet() ) {
//			int cost = resource.getValue();
//			int available = getResources().get(resource.getKey());
//			int rest = available - cost;
//			getResources().put(resource.getKey(), rest);
//		}
//		
//	}

	private boolean testCanBuild(int[] requiredResources) {
		for (int key = 0; key <requiredResources.length;key++) {
			int cost = requiredResources[key];
			int available = building.getAvailableResources(key);
			int rest = cost - available;
			if (rest >0) {
//				if (resourceShortage == null) {
//					resourceShortage = new int[resourceInfo.NUM_RESOURCES];
//				}
//				resourceShortage[key] = rest;
				return false;
			}
		}
		
		return true;
	}

}
