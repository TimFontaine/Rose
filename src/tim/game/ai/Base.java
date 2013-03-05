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

	/**
	 * 
	 */
	public Base() {
		factoryList = new ArrayList<Factory>();
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
			ResourceInfo info = GameApplicationFactory.getInstance().getResourceInfo();
			Map<String, Integer> resources = info.getResourcesForThing("factory");
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
			Map<String,Integer> totalResources = new HashMap<String, Integer>();
			for (Node node : nodeList) {
				if (node.containsMapItem()) {
					Item item = node.getItem();
					if (item instanceof Factory) {
						//player or Base can give factory orders
						Map<String,Integer> resources = calcRequiredResources(item);
						addResources(totalResources, resources);
						goal.setDestination(item.getLocation());
					}
					
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
	private void addResources(Map<String, Integer> total,
			Map<String, Integer> temp) {
		for (Map.Entry<String, Integer> entry : temp.entrySet()) {
			if (total.containsKey(entry.getKey())) {
				int available = total.get(entry.getKey());
				int newValue = available + entry.getValue();
				total.put(entry.getKey(), newValue);
			} else {
				total.put(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * @param string
	 */
	private Map<String,Integer> calcRequiredResources(Item item) {
		Factory factory = (Factory) item;
		Map<String,Integer> resources = factory.getRequiredResources();
		return resources;
	}

}
