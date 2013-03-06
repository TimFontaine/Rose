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
	private Map<String, int[]> resources;
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
	
	/**
	 * 
	 */
	private ResourceInfo() {
		location = new HashMap<String, String>();
		location.put("iron", "mine");
		location.put("oil", "oilwell");
		
		int ironKey = getResourceKeyByName("iron");
		int oilKey = getResourceKeyByName("oil");
		
		resources = new HashMap<String, int[]>();
		int[] workerCost = new int[2];
		workerCost[ironKey] = 20;
		workerCost[oilKey] = 10;
		
		resources.put("worker", workerCost);
		
		int[] farmCost = new int[2];
		farmCost[ironKey] = 20;
		farmCost[oilKey] = 10;
		resources.put("farm", farmCost);
		
		int[] factoryCost = new int[2];
		factoryCost[ironKey] = 20;
		factoryCost[oilKey] = 10;
		resources.put("factory", factoryCost);
		
	}
	
	public AvailableResources getResourceByKey(int key) {
		return AvailableResources.values()[key];
	}
	
	public int getResourceKeyByName(String name) {
		return AvailableResources.valueOf(name.toUpperCase()).getNum();
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
	public int[] getResourcesForThing(String name) {
		return resources.get(name);
	}

	/**
	 * @param key
	 * @return
	 */
	public String getLocation(int key) {
		AvailableResources a = getResourceByKey(key);
		return getLocation(a.toString().toLowerCase());
		
	}

}
