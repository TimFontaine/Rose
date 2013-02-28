/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


import tim.data.ai.ActionType;
import tim.data.ai.PlayerOrder;
import tim.data.ai.PlayerPriority;
import tim.data.ai.ProductionPlanner;
import tim.data.back.Building;
import tim.data.back.Event;
import tim.data.back.RoseObject;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.data.unit.UnitState;
import tim.data.unit.Worker;
import tim.game.Player;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 *
 */
public class SimplePlayerAI extends RoseObject implements Player {
	
	List<Unit> units; 
	List<Building> buildings;
	
	
	PlayerPriority priority;
	
	//temp values
	int productionCapacity;
	int productionUsed;
	
	Queue<PlayerOrder> playerOrders;
	SortedSet<ResourcesRequest> requestsQueue;

	/**
	 * 
	 */
	public SimplePlayerAI(String name) {
		super(name);
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		playerOrders = new LinkedList<PlayerOrder>();
		requestsQueue = new TreeSet<ResourcesRequest>();
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUnit(tim.data.unit.Unit)
	 */
	@Override
	public void addUnit(Unit unit) {
		units.add(unit);

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#doLogic()
	 */
	@Override
	public void doLogic() {
		playerOrders.clear();
		requestsQueue.clear();
		if (priority == PlayerPriority.PRODUCTION_CAPACITY) {
			ProductionPlanner planner = new ProductionPlanner();
			Collection<ResourcesRequest> requests = planner.doAction();
			requestsQueue.addAll(requests);
		}
		
		for (Building building: buildings) {
			building.doLogic();
			if (!building.getRequestMap().isEmpty()) {
				Map<String, Integer> request = building.getRequestMap();
				//build a new playerorder
				PlayerOrder order = new PlayerOrder();
				order.setAction(ActionType.RESOURCES);
				order.setX(building.getX());
				order.setY(building.getY());
				order.setTypeName(building.getType());
				order.setProcessorType("worker");
				if (request.containsKey("iron")) {
					order.setIron(request.get("iron"));
				} 
				if (request.containsKey("oil")) {
					order.setIron(request.get("oil"));
				} 
				
				playerOrders.add(order);
			}
		}
		
		defineUnitPriorities();
		for (Unit unit: units) {
			if (unit.getState() == UnitState.IDLE) {
				if (!requestsQueue.isEmpty()) {
					ResourcesRequest request = requestsQueue.first();
					/*
					 * TODO replace by lowering calculator
					 */
					request.setPriority(request.getPriority()-50);
					PlayerOrder order = translateRequest(request);
					
					
					if (unit.getType().equals(order.getProcessorType())) {
						((Worker)unit).setPlayerOrder(order);
					}
				}
			}
			unit.doLogic();
		}

	}

	/**
	 * @param request
	 * @return
	 */
	private PlayerOrder translateRequest(ResourcesRequest request) {
		PlayerOrder order = new PlayerOrder();
		if (request.getRequestType() == RequestType.PRODUCTION) {
			order.setIron(request.getRequest().get("iron"));
			order.setOil(request.getRequest().get("oil"));
			order.setX(14);
			order.setY(14);
			order.setTypeName("factory");
			order.setProcessorType("worker");
			order.setAction(ActionType.BUILD);
		} else if (request.getRequestType() == RequestType.RESOURCES) {
			order.setAction(ActionType.RESOURCES);
			order.setX(request.getLocation().x);
			order.setY(request.getLocation().y);
//			order.setTypeName(building.getType());
			order.setProcessorType("worker");
			if (request.getRequest().containsKey("iron")) {
				order.setIron(request.getRequest().get("iron"));
			} 
			if (request.getRequest().containsKey("oil")) {
				order.setIron(request.getRequest().get("oil"));
			} 
		}
		return order;
	}

	/**
	 * 
	 */
	private void defineUnitPriorities() {
		//1. gather all priorities
		//1a. buildings only request for workers
		for (Building building: buildings) {
			requestsQueue.add(building.getResourcesRequest());
		}
		
		
		
		if (productionCapacity == 0) {//there is no production capacity
			priority = PlayerPriority.PRODUCTION_CAPACITY;
		} else if (productionCapacity < 5) {//it takes too long to get resources, build roads 
			priority = PlayerPriority.ROADS;
		} else { //only suply resources
			priority = PlayerPriority.RESOURCES;
		}
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addEvent(tim.data.back.Event)
	 */
	@Override
	public void addEvent(Event event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#orderFinished(tim.data.unit.Unit, tim.data.unit.UnitOrder)
	 */
	@Override
	public void orderFinished(Unit source, UnitOrder order) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#initTurn()
	 */
	@Override
	public void initTurn() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addBuilding(tim.data.back.Building)
	 */
	@Override
	public void addBuilding(Building building) {
		buildings.add(building);
		if ("factory".equals(building.getType())) {
			productionCapacity+=6;
		}

	}

}
