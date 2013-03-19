/**
 * 
 */
package tim.game.ai.data;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class ResourceInfo {
	
	private Map<String, String> location;
	private Map<String, EnumMap<Resource, Integer>> resources;
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
		
		resources = new HashMap<String, EnumMap<Resource, Integer>>();
		EnumMap<Resource, Integer> workerCost = new EnumMap<MutableResource.Resource, Integer>(Resource.class);
		workerCost.put(Resource.IRON, 20);
		workerCost.put(Resource.OIL, 10);
		
		resources.put("worker", workerCost);
		
		EnumMap<Resource, Integer> farmCost = new EnumMap<MutableResource.Resource, Integer>(Resource.class);
		farmCost.put(Resource.IRON, 20);
		farmCost.put(Resource.OIL, 10);
		resources.put("farm", farmCost);
		
		EnumMap<Resource, Integer> factoryCost = new EnumMap<MutableResource.Resource, Integer>(Resource.class);
		factoryCost.put(Resource.IRON, 20);
//		factoryCost.put(Resource.OIL, 10);
		resources.put("factory", factoryCost);
		
		EnumMap<Resource, Integer> storageCost = new EnumMap<MutableResource.Resource, Integer>(Resource.class);
		storageCost.put(Resource.IRON, 20);
		storageCost.put(Resource.OIL, 10);
		resources.put("storage", storageCost);
		
		EnumMap<Resource, Integer> soldierCost = new EnumMap<MutableResource.Resource, Integer>(Resource.class);
		soldierCost.put(Resource.IRON, 20);
		soldierCost.put(Resource.OIL, 10);
		resources.put("infantery", soldierCost);
		
		
		
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
	public EnumMap<Resource, Integer> getResourcesForThing(String name) {
		return resources.get(name);
	}
	
	public EnumMap<Resource, Integer> getUsageToBuild() {
		EnumMap<Resource, Integer> map = new EnumMap<Resource, Integer>(Resource.class);
		map.put(Resource.IRON, 10);
		return map;
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
