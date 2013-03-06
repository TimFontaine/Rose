/**
 * 
 */
package tim.data.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class Factory extends Building {

	private int producedUnits;
	
	private Unit unitToProduce;
	
	private int[] resourceShortage;
	
	GameApplicationFactory applicationFactory;
	ResourceInfo resourceInfo;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		setType("factory");
		applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		System.out.println("new factory");
		
	}
	
	public void doLogic() {
		ResourceInfo info = applicationFactory.getResourceInfo();
		int[] requiredResources = info.getResourcesForThing("worker");
		boolean test = testCanBuild(requiredResources);
		if (test) {
			buildUnit(requiredResources);
			resourceShortage = null;
		} else {
			
			
			
		}
	}
	
	private void buildUnit(int[] requiredResources) {
		Unit unit = (Unit) RoseObjectFactory.getInstance().getRoseObject("worker");
		unit.setName("worker");
		unit.setType("worker");
		unit.setPlayer(player);
		unit.setOil(100);
		unit.setX(x);
		unit.setY(y);
		back.addUnit(unit);
		retreiveMultipleResources(requiredResources);
		System.out.println("factory has build unit");
		producedUnits++;
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
		//test that the factory is finished constructing
		if (getState() == BuildingState.CONSTRUCTING) {
			return false;
		}
		
		for (int key = 0; key <requiredResources.length;key++) {
			int cost = requiredResources[key];
			int available = resources[key];
			int rest = cost - available;
			if (rest >0) {
				if (resourceShortage == null) {
					resourceShortage = new int[resourceInfo.NUM_RESOURCES];
				}
				resourceShortage[key] = rest;
				return false;
			}
		}
		return true;
	}
	
	/**
	 * a list of the required resources to continue the action
	 * @return map with the name and amount of each required resource
	 */

	public int getProducedUnits() {
		return producedUnits;
	}

	public void setProducedUnits(int producedUnits) {
		this.producedUnits = producedUnits;
	}

	public Unit getUnitToProduce() {
		return unitToProduce;
	}

	public void setUnitToProduce(Unit unitToProduce) {
		this.unitToProduce = unitToProduce;
	}

	public int[] getResourceShortage() {
		return resourceShortage;
	}

	public void setResourceShortage(int[] resourceShortage) {
		this.resourceShortage = resourceShortage;
	}

}
