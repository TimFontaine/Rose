/**
 * 
 */
package tim.game.ai.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tfontaine
 *
 */
public class ResourceInfo {
	
	private Map<String, String> location;
	private Map<String, Map<String, Integer>> resources;
	private static ResourceInfo INSTANCE;

	/**
	 * 
	 */
	private ResourceInfo() {
		location = new HashMap<String, String>();
		location.put("iron", "mine");
		location.put("oil", "oilwell");
		
		resources = new HashMap<String, Map<String,Integer>>();
		Map<String, Integer> workerCost = new HashMap<String, Integer>();
		workerCost.put("iron", 20);
		workerCost.put("oil", 10);
		
		resources.put("worker", workerCost);
		
		
	}
	
	public static ResourceInfo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ResourceInfo();
		}
		return INSTANCE;
	}
	
	public  String getLocation(String name) {
		return location.get(name);
	}

	/**
	 * @return the itemCost
	 */
	public Map<String, Integer> getResourcesForThing(String name) {
		return resources.get(name);
	}

}
