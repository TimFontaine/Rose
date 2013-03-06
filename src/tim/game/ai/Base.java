/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.data.ai.ActionType;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.game.ai.data.Goal;
import tim.game.ai.data.Grid;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class Base extends Grid {
	
	private int production;
	
	List<Factory> factoryList;
	ResourceInfo resourceInfo;
	

	/**
	 * 
	 */
	public Base() {
		factoryList = new ArrayList<Factory>();
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
	}
	
	public void evaluate() {
		for (Node node : nodeList) {
			//calc the total production
			if (node.containsItem() && node.getItem() instanceof Factory) {
				production++;
				factoryList.add((Factory)node.getItem());
				
			}
		}
	}
	
	public Goal defineGoal() {
		evaluate();
		if (production == 0) {
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
			return goal;
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
				if (node.containsMapItem()) {
					Item item = node.getItem();
					if (item instanceof Factory) {
						//player or Base can give factory orders
						//for now let the factory produce workers
						//1. get the required resources to build unit(worker)
						int[] required = resourceInfo.getResourcesForThing("worker");
						//2. calc the extra resources that are not available
						int[] resources = calcRequiredResources(required, (Thing)item);
						//resources required
						addResources(totalResources, resources);
						}
						goal.setDestination(item.getLocation());
					}
					
				}
				goal.setResources(totalResources);
				goal.setActionType(actionType);
				setGoal(goal);
				return goal;
			}
			
			
//		Goal goal = new Goal();
//		goal.setActionType(ActionType.NONE);
//		return goal;
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
		int available[] = thing.getResources();
		for (int key= 0; key < requirement.length;key++) {
			int availableRes = available[key];
			int needed = requirement[key];
			if (availableRes - needed < 0) {
				required[key] = needed - availableRes;
			}
		}
 		return required;
	}

}
