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
	
	private int[] requiredResources;
	
	GameApplicationFactory applicationFactory;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		setType("factory");
		applicationFactory = GameApplicationFactory.getInstance();
		ResourceInfo resourceInfo = applicationFactory.getResourceInfo();
		requiredResources = new int[resourceInfo.NUM_RESOURCES];
		System.out.println("new factory");
		
	}
	
	public void doLogic() {
		ResourceInfo info = applicationFactory.getResourceInfo();
		requiredResources = info.getResourcesForThing("worker");
		boolean test = testCanBuild();
		if (test) {
			buildUnit();
		} else {
			
			
			
		}
	}
	
	private void buildUnit() {
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

	private boolean testCanBuild() {
		for (int key = 0; key <requiredResources.length;key++) {
			int cost = requiredResources[key];
			int available = resources[key];
			int rest = available - cost;
			if (rest <0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * a list of the required resources to continue the action
	 * @return map with the name and amount of each required resource
	 */
	public int[] getRequiredResources() {
		return requiredResources;
	}

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

	public void setRequiredResources(int[] requiredResources) {
		this.requiredResources = requiredResources;
	}

}
