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

	private Map<String, Integer> resources;
	
	private int producedUnits;
	
	private Unit unitToProduce;
	
	private Map<String, Integer> requiredResources;
	
	
	
	GameApplicationFactory applicationFactory;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		setType("factory");
		applicationFactory = GameApplicationFactory.getInstance();
		setResources(new HashMap<String, Integer>());
		requiredResources = new HashMap<String, Integer>();
		ResourceInfo resourceInfo = applicationFactory.getResourceInfo();
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
		handleResourseCost();
		System.out.println("factory has build unit");
		producedUnits++;
	}

	private void handleResourseCost() {
		Map<String,Integer> required = requiredResources;
		for (Map.Entry<String, Integer> resource : required.entrySet() ) {
			int cost = resource.getValue();
			int available = getResources().get(resource.getKey());
			int rest = available - cost;
			getResources().put(resource.getKey(), rest);
		}
		
	}

	private boolean testCanBuild() {
		Map<String,Integer> required = requiredResources;
		for (Map.Entry<String, Integer> resource : required.entrySet() ) {
			if (!getResources().containsKey(resource.getKey())) {
				return false;
			}
			int cost = resource.getValue();
			int available = getResources().get(resource.getKey());
			int rest = available - cost;
			if (rest < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * a list of the required resources to continue the action
	 * @return map with the name and amount of each required resource
	 */
	public Map<String, Integer> getRequiredResources() {
		return requiredResources;
	}


	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#receiveResource(java.lang.String, int)
	 */
	@Override
	public void receiveResource(String name, int amount) {
		resources.put(name, amount);
		
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#giveResource(java.lang.String, int)
	 */
	@Override
	public void giveResource(String name, int amount) {
		// TODO Auto-generated method stub
		
	}

	public int getProducedUnits() {
		return producedUnits;
	}

	public void setProducedUnits(int producedUnits) {
		this.producedUnits = producedUnits;
	}

	/**
	 * @return the resources
	 */
	public Map<String, Integer> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Map<String, Integer> resources) {
		this.resources = resources;
	}

	public Unit getUnitToProduce() {
		return unitToProduce;
	}

	public void setUnitToProduce(Unit unitToProduce) {
		this.unitToProduce = unitToProduce;
	}

}
