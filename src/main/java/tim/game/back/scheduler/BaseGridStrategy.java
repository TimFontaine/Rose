/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import tim.data.back.Node;
import tim.data.building.Building;
import tim.game.ai.ResourcesData;
import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.ai.data.ResourceInfo;
import tim.game.back.scheduler.Order.OrderAction;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tim
 *
 */
public class BaseGridStrategy implements GridStrategy {
	
	ResourceInfo resourceInfo;
	
	private List<Order> orderList;
	private Building base;
	
	private EnumMap<Resource, MutableResource> resourceLink;
	GridData data;
	
	private int factoryOrders;
	
	/**
	 * 
	 */
	public BaseGridStrategy(GridData data) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		this.data = data;
		init();
	}

	/**
	 * 
	 */
	private void init() {
		//set resource link
		this.resourceLink = data.getBase().getResources();
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#doAction()
	 */
	@Override
	public void doAction() {
		/**
		 * TODO clear or new instance ?
		 */
		orderList = new ArrayList<Order>();
		ResourceOrder order = new ResourceOrder();
		order.setAction(OrderAction.RESOURCES);
		order.setAmount(10);
		order.setDestination(data.getBase().getLocation());
		order.setPriority(100);
		orderList.add(order);
		
		Resource lowestResource = lowestResource();
		order.setResource(lowestResource);
		
		if (factoryOrders == 0) {
			addBuildings();
		}
		
		
	}
	
	private Resource lowestResource() {
		EnumMap<Resource, MutableResource> resources = data.getBase().getResources();
		int lowest = Integer.MAX_VALUE;
		Resource lowestResource = null;
		for (Resource resource : Resource.values()) {
			if (resources.containsKey(resource)) {
				int amount = resources.get(resource).getAmount();
				if (amount < lowest) {
					lowestResource = resource;;
					lowest = amount;
				}
			} else {
				return resource;
			}
		}
		return lowestResource;
	}

	/**
	 * 
	 */
	private void addBuildings() {
		//test enough resources;
		EnumMap<Resource, Integer> map = resourceInfo.getResourcesForThing("factory");
		boolean canBuild = true;
		for (Map.Entry<Resource, Integer> entry : map.entrySet()) {
			if (resourceLink.containsKey(entry.getKey())) {
				MutableResource mutable = resourceLink.get(entry.getKey());
				if (mutable.getAmount() < entry.getValue()) {
					canBuild = false;
				}
			} else {
				canBuild = false;
			}
		}
		if (canBuild) {
			addBuildingOrder("factory", map);
			factoryOrders++;
		}
	}

	/**
	 * @param string
	 * @param map
	 */
	private void addBuildingOrder(String string, EnumMap<Resource, Integer> map) {
		Order order = new Order();
		order.setAction(OrderAction.BUILD);
		Node destination = getFreeNode(data.getNodes());
		order.setDestination(destination.getLocation());
		order.setPriority(120);
		orderList.add(order);
	}
	
	private Node getFreeNode(List<Node> nodes) {
		for (Node node : nodes) {
			if (!node.containsItem()) {
				return node;
			}
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#getOrders()
	 */
	public List<Order> getOrders() {
		return orderList;
	}

	public Building getBase() {
		return base;
	}

}
