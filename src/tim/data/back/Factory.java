/**
 * 
 */
package tim.data.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class Factory extends Building {

	private Map<String, Integer> resources;
	
	private int producedUnits;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		setType("factory");
		setResources(new HashMap<String, Integer>());
		System.out.println("new factory");
	}
	
	public void doLogic() {
		boolean test = testCanBuild();
		if (test) {
			//buildUnit();
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
		Map<String,Integer> required = getResourcesRequest().getResource();
		for (Map.Entry<String, Integer> resource : required.entrySet() ) {
			int cost = resource.getValue();
			int available = getResources().get(resource.getKey());
			int rest = available - cost;
			getResources().put(resource.getKey(), rest);
		}
		
	}

	private boolean testCanBuild() {
		Map<String,Integer> required = getResourcesRequest().getResource();
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

}
