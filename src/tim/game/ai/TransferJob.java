/**
 * 
 */
package tim.game.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.data.back.Building;
import tim.data.back.Event;
import tim.data.back.EventCode;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class TransferJob extends MoveJob {

	Task task;
	Map<String, Path> map;
	String destinationName;
	
	
	
	/**
	 * 
	 */
	public TransferJob(Unit unit, Task task) {
		this.task = task;
		this.unit = unit;
		map = new HashMap<String, Path>();
		onDestination();
	}

	@Override
	protected void onDestination() {
		Node destination = back.getMap().getNode(unit.getX(), unit.getY());
		Building building = destination.getBuilding();
		Item item = destination.getItem();
		if (item != null && "mine".equals(item.getName())) {
			//pickup goods
			unit.setResources(Unit.RESOURCE_TRANSFER);
			resourcePickupEvent();
		} else if (building != null && "factory".equals(building.getName())) {
			//deliver goods
			((Factory)building).receiveIron(unit.getResources());
			unit.setResources(unit.getResources() - Unit.RESOURCE_TRANSFER);
			resourceDeliveryEvent();
		}
		
		//switch destination
		step = 0;
		destinationName = task.getNext();
		if (!map.containsKey(destinationName)){
			//find nearest object
			path = back.findNearestObject(unit, destinationName);
			map.put(destinationName, path);
		} else {
			path = map.get(destinationName);
			
		}
		this.destination = path.getLast();
		
	}
	
	public List<Path> getUsedPaths() {
		return null;
	}
	
	private void resourcePickupEvent() {
		//oil is empty
		Event event = new Event();
		event.setSource(unit);
		event.setCode(EventCode.RESOURCE_PICKUP);
		event.setDescription("resource pickup");
		back.getEvents().add(event);
	}
	
	private void resourceDeliveryEvent() {
		//oil is empty
		Event event = new Event();
		event.setSource(unit);
		event.setCode(EventCode.RESOURCE_DELIVERY);
		event.setDescription("resource delivery");
		back.getEvents().add(event);
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#getDestinaton()
	 */
	@Override
	public Point getDestinaton() {
		// TODO Auto-generated method stub
		return null;
	}


}
