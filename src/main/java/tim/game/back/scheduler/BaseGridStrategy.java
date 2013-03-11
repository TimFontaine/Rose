/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import tim.data.back.Building;
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
	
	/**
	 * 
	 */
	public BaseGridStrategy(GridData data) {
		orderList = new ArrayList<Order>();
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		
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
		ResourceOrder order = new ResourceOrder();
		order.setAction(OrderAction.RESOURCES);
		order.setAmount(10);
		order.setDestination(data.getBase().getLocation());
		order.setResource(Resource.IRON);
		order.setPriority(100);
		orderList.add(order);
		
		addBuildings();
	}

	/**
	 * 
	 */
	private void addBuildings() {
		//test enough resources;
		EnumMap<Resource, Integer> map = resourceInfo.getResourcesForThing("factory");
		for (Map.Entry<Resource, Integer> entry : map.entrySet()) {
			if (resourceLink.containsKey(entry.getKey())) {
				MutableResource mutable = resourceLink.get(entry.getKey());
				if (mutable.getAmount() > entry.getValue()) {
					//fixit
					addBuildingOrder("factory", map);
				}
			}
		}
	}

	/**
	 * @param string
	 * @param map
	 */
	private void addBuildingOrder(String string, EnumMap<Resource, Integer> map) {
		
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
