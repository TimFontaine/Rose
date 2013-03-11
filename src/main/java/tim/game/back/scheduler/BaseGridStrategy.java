/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Building;
import tim.game.ai.ResourcesData;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class BaseGridStrategy implements GridStrategy {
	
	private List<Order> orderList;
	private Building base;
	
	private ResourcesData resourcesLink;

	/**
	 * 
	 */
	public BaseGridStrategy() {
		orderList = new ArrayList<Order>();
	}

	/* (non-Javadoc)
	 * @see tim.game.back.scheduler.GridStrategy#doAction()
	 */
	@Override
	public void doAction() {
		ResourceOrder order = new ResourceOrder();
		order.setDestination(new Point(1,1));
		order.setAction(OrderAction.RESOURCES);
		order.setAmount(10);
		order.setDestination(base.getLocation());
		order.setResource(Resource.IRON);
		orderList.add(order);
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

	public void setBase(Building base) {
		this.base = base;
		this.resourcesLink =base.getResourcesData();
	}

}
