/**
 * 
 */
package tim.game.ai.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class ResourceInfo {
	
	private Map<String, String> location;
	private Map<String, EnumMap<Resource, Integer>> resources;
	private Map<String,List<String>> unitActions;
	private Map<String,List<String>> buildingActions;
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
		unitActions = new HashMap<String, List<String>>();
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
		resources.put("infantry", soldierCost);
		
		initUnitActions();
		initBuildingActions();
		
	}
	
	/**
	 * 
	 */
	private void initBuildingActions() {
		List<String> factoryActions = new ArrayList<String>();
		factoryActions.add("worker");
		factoryActions.add("infantry");
		unitActions.put("factory", factoryActions);
	}

	/**
	 * 
	 */
	private void initUnitActions() {
		List<String> workerActions = new ArrayList<String>();
		workerActions.add("factory");
		workerActions.add("storage");
		unitActions.put("worker", workerActions);
		
		List<String> infantryActions = new ArrayList<String>();
		infantryActions.add("hide");
		unitActions.put("infantry", infantryActions);
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
	
	public List<String> getUnitActions(String actorName) {
		return unitActions.get(actorName);
	}
	
	public List<String> getBuildingActions(String actorName) {
		return unitActions.get(actorName);
	}

}
