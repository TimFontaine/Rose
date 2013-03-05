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

	public enum AvailableResources {
		OIL(0),
		IRON(1),
		MUNITION(2);
		
		private int num;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		AvailableResources(int num) {
			this.num = num;
		}
	}

	public int NUM_RESOURCES = 3;
	
	private int[][] res;
	
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
		
		Map<String, Integer> farmCost = new HashMap<String, Integer>();
		farmCost.put("iron", 20);
		farmCost.put("oil", 10);
		resources.put("farm", farmCost);
		
		Map<String, Integer> factoryCost = new HashMap<String, Integer>();
		factoryCost.put("iron", 20);
		factoryCost.put("oil", 10);
		resources.put("factory", factoryCost);
		
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
