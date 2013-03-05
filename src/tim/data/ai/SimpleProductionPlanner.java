/**
 * 
 */
package tim.data.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import tim.data.back.Building;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class SimpleProductionPlanner {
	
	private GameApplicationFactory applicationFactory;
	private ResourceInfo resourceInfo;
	private List<Building> buildings;
	
	private SortedSet<ResourcesRequest> requests;
	
	private SortedMap<String, Integer> buildMap;
	

	/**
	 * Option 1 build farm
	 * Option 2 build worker
	 * Option 3 build factory 
	 */
	public SimpleProductionPlanner() {
		applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		requests = new TreeSet<ResourcesRequest>();
		buildMap = new TreeMap<String, Integer>();
		
		buildMap.put("factory", 1);
		buildMap.put("farm", 2);
		
	}
	
	public void createPlanning() {
		//1. build farm
		expandProduction();
		//2. build 
		examineBuildings();
	}

	/**
	 * 
	 */
	private void examineBuildings() {
		for (Building building : buildings) {
			//assert building is a factory
			//a factory can produce a new worker
			Map<String,Integer> required = resourceInfo.getResourcesForThing("worker");
			ResourcesRequest request = new ResourcesRequest();
			request.setRequestType(RequestType.PRODUCTION);
			request.setResource(required);
			request.setPriority(50);
			requests.add(request);
		}
	}

	/**
	 * factory + farm required
	 */
	private void expandProduction() {
		for (SortedMap.Entry<String,Integer> entry  : buildMap.entrySet()) {
			Map<String,Integer> required = resourceInfo.getResourcesForThing(entry.getKey());
			ResourcesRequest request = new ResourcesRequest();
			request.setRequestType(RequestType.PRODUCTION);
			request.setResource(required);
			request.setPriority(entry.getValue());
			requests.add(request);
		}
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public SortedSet<ResourcesRequest> getRequests() {
		return requests;
	}

	public void setRequests(SortedSet<ResourcesRequest> requests) {
		this.requests = requests;
	}

	
}
