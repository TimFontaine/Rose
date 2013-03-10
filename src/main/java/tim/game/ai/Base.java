/**
 * 
 */
package tim.game.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.data.ai.ActionType;
import tim.data.back.Building;
import tim.data.back.BuildingState;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.game.ai.data.Goal;
import tim.game.ai.data.Goal.GoalStatus;
import tim.game.ai.data.Grid;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class Base extends Grid {
	
	private int production;
	
	List<Building> factoryList;
	List<Building> underConstruction;
	ResourceInfo resourceInfo;
	
	ResourcesData resourcesData;
	
	private Map<String, Goal> assignments;
	
	//STATES
	private boolean STORAGE_REQUIRED;
	private boolean PRODUCTION_REQUIRED;

	/**
	 * 
	 */
	public Base() {
		factoryList = new ArrayList<Building>();
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		resourcesData = new ResourcesData();
		assignments = new HashMap<String, Goal>();
		underConstruction = new ArrayList<Building>();
	}
	
	public void evaluate() {
		factoryList.clear();
		
		for (Map.Entry<String, Goal> entry : assignments.entrySet()) {
			Goal goal = entry.getValue();
			if (goal.getAssignedUnits().size() == 0) {
				goal.setStatus(GoalStatus.FINISHED);
				assignments.remove(entry.getKey());
			}
		}
		
		for (Node node : nodeList) {
			//calc the total production
			/**
			 * TODO store all buildings in a list
			 */
			if (node.containsItem() && node.getItem().getType().equals("factory")) {
				Building factory = (Building) node.getItem();
				if (factory.getState() == BuildingState.CONSTRUCTING) {
					underConstruction.add(factory);
				} else {
					production++;
				}
				
				factoryList.add(factory);
			}
		}
			
			//INIT STATES
			/**
			 * TODO
			 * reset states function
			 */
			if (production == 0) {
				PRODUCTION_REQUIRED = true;
			} else {
				PRODUCTION_REQUIRED = false;
			}
			
			STORAGE_REQUIRED = false;
			//first check, is there any storage request
				if (assignments.containsKey("storage")) {
					System.out.println("base has storage assignment with status" + assignments.get("storage").getStatus());
					if (assignments.get("storage").getStatus() == GoalStatus.FINISHED) {
						assignments.remove("storage");
					}
				}
				for (Building factory : factoryList) {
					if (!factory.hasResources()) {
						if (!assignments.containsKey("storage") || 
								assignments.get("storage").getStatus() != GoalStatus.INPROGRESS) {
							STORAGE_REQUIRED = true;
						}
					}
				}
//			} else {
//				STORAGE_REQUIRED = false;
//				System.out.println("storage goal is in assignment list");
//			}
	}
	
	public List<Goal> defineGoal() {
		List<Goal> goalList = new ArrayList<Goal>();
		evaluate();
		if (!underConstruction.isEmpty()) {
			//there are buildings under construction, bring resources to them
			for (Building building : underConstruction) {
				Goal goal = new Goal();
				ActionType actionType = ActionType.RESOURCES;
				goal.setActionType(actionType);
				goal.setDestination(building.getLocation());
				goal.setResources(calcMissingResources(building));
				goal.setPriority(200);
				goalList.add(goal);
			}
		}
		
		if(STORAGE_REQUIRED) {
			ActionType actionType = ActionType.BUILD;
			actionType.setInfo("storage");
			goal.setActionType(actionType);
			goal.setPriority(100);
			goal.setProcessor("worker");
			int[] resources = resourceInfo.getResourcesForThing("storage");
			goal.setResources(resources);
			goal.setDestination(getFreeNode().getLocation());
			setGoal(goal);
			goalList.add(goal);
			assignments.put("storage", goal);
		}
		if (PRODUCTION_REQUIRED) {
			Goal goal = new Goal();
			ActionType actionType = ActionType.BUILD;
			actionType.setInfo("factory");
			goal.setActionType(actionType);
			goal.setPriority(100);
			goal.setProcessor("worker");
			int[] resources = resourceInfo.getResourcesForThing("factory");
			goal.setResources(resources);
			goal.setDestination(getFreeNode().getLocation());
			setGoal(goal);
			goalList.add(goal);
		} else {
			//there is a factory, ask for resources
			Goal goal = new Goal();
			ActionType actionType = ActionType.RESOURCES;
			/**
			 * TODO
			 * find the best place to stock resources
			 * store buildings
			 */
			int[] totalResources = new int[resourceInfo.NUM_RESOURCES];
			for (Node node : nodeList) {
				if (node.containsItem()) {
					Item item = node.getItem();
					if (item.getType().equals("factory")) {
						//player or Base can give factory orders
						//for now let the factory produce workers
						//1. get the required resources to build unit(worker)
						int[] required = resourceInfo.getResourcesForThing("worker");
						//2. calc the extra resources that are not available
						int[] resources = calcRequiredResources(required, (Thing)item);
						//resources required
						addResources(totalResources, resources);
						goal.setDestination(item.getLocation());
					}
					
				}
				
					
			}
			goal.setResources(totalResources);
			goal.setActionType(actionType);
			goalList.add(goal);
			setGoal(goal);
		}
		
		if (goalList.isEmpty()) {
			System.out.println("ERROR: goallist in base is empty");
		}
		return goalList;
			
//		Goal goal = new Goal();
//		goal.setActionType(ActionType.NONE);
//		return goal;
	}
	
	

	/**
	 * @param building
	 * @return
	 */
	private int[] calcMissingResources(Building building) {
//		//update the required resources
//		int[] requirement = new int[requiredResources.length];
//		for (int key = 0; key < requiredResources.length; key++) {
//			int required = requiredResources[key] - building.getAvailableResources(key);
//			if (required > 0) {
//				requirement[key] = required;
//			}
//		}
//		building.setRequiredResources(requiredResources);
		return new int[2];
	}

	/**
	 * @param totalResources
	 * @param resources
	 */
	private void addResources(int[] total,
			int[] temp) {
		for (int i = 0; i <temp.length;i++) {
			int extra = temp[i];
			int newAmount =  total[i] + extra;
			total[i] = newAmount;
		}
		
	}

	/**
	 * @param string
	 */
	private int[] calcRequiredResources(int[] requirement,  Thing thing) {
		int[] required = new int[resourceInfo.NUM_RESOURCES];
		int available[] = thing.getAvailableResources();
		for (int key= 0; key < requirement.length;key++) {
			int availableRes = available[key];
			int needed = requirement[key];
			if (availableRes - needed < 0) {
				required[key] = needed - availableRes;
			}
		}
 		return required;
	}
	
	private void addAssignedJob(String name, MapItem mapItem) {
		
	}

}
