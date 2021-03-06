/**
 * 
 */
package tim.game.ai;

import java.awt.Point;
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
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.back.RoseObject;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.data.unit.UnitState;
import tim.data.unit.Worker;
import tim.game.Back;
import tim.game.Player;
import tim.game.ai.data.Grid;
import tim.game.ai.data.PlayerData;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class SimplePlayerAI extends RoseObject implements Player {
	
	List<Unit> units; 
	List<Building> buildings;
	List<Item> usedItems;
	
	
	PlayerPriority priority;
	
	//temp values
	int productionCapacity;
	int productionUsed;
	
	Queue<PlayerOrder> playerOrders;
	SortedSet<ResourcesRequest> requestsQueue;
	
	private PlayerData data;
	private List<Grid> grids;

	/**
	 * 
	 */
	public SimplePlayerAI(String name) {
		super(name);
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		playerOrders = new LinkedList<PlayerOrder>();
		requestsQueue = new TreeSet<ResourcesRequest>();
		usedItems = new ArrayList<Item>();
		data = new PlayerData();
		grids = new ArrayList<Grid>();
		
		setupGrids();
	}

	/**
	 * 
	 */
	private void setupGrids() {
		//define a grid for the base;
		Grid grid = new Grid();
		//assign nodes to a grid;
		Node[][] nodes = new Node[3][3];
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		//this is the upper left of the grid
		Point gridPoint = new Point(13,13);
		Back back = applicationFactory.getBack();
		//fill the grid nodes
		for (int i = 0; i <nodes.length; i++) {
			for (int j = 0; j< nodes[0].length; j++) {
				nodes[i][j] =  back.getNode(gridPoint.x + i, gridPoint.y +j);
			}
		}
		grid.setNodes(nodes);
		grids.add(grid);
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
		ProductionPlanner planner = new ProductionPlanner(data);
		Collection<ResourcesRequest> requests = planner.doAction();
		requestsQueue.addAll(requests);
		defineUnitPriorities();
		for (Building building: buildings) {
			building.doLogic();
		}
		
		
		for (Unit unit: units) {
			if (unit.getState() == UnitState.IDLE) {
				if (!requestsQueue.isEmpty()) {
					ResourcesRequest request = requestsQueue.first();
					/*
					 * TODO replace by lowering calculator
					 */
					request.setPriority(request.getPriority()-50);
					System.out.println(request.getRequestType().toString()+ ":" + request.getPriority());
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
//			order.setIron(request.getResource().get("iron"));
//			order.setOil(request.getResource().get("oil"));
			Map<String, Integer> resources = request.getResource();
			order.setResources(resources);
			order.getInfo().put("start", new Point(14,14));
			order.setTypeName("factory");
			order.setProcessorType("worker");
			order.setAction(ActionType.BUILD);
		} else if (request.getRequestType() == RequestType.RESOURCES) {
			order.setAction(ActionType.RESOURCES);
			order.getInfo().put("start", request.getLocation());
//			order.setTypeName(building.getType());
			order.setProcessorType("worker");
			Map<String, Integer> resources = request.getResource();
			order.setResources(resources);
		} else if (request.getRequestType() == RequestType.ROAD) {
			/**
			 * TODO
			 * make an intelligent road system
			 */
			Point start = getBuildingOfType("factory").getLocation();
			Point end = usedItems.get(0).getLocation();
			order.setAction(ActionType.ROAD);
			order.getInfo().put("start", start);
			order.getInfo().put("end", end);
			order.setAction(ActionType.ROAD);
			order.setProcessorType("worker");
			
		}
		return order;
	}
	
	private Building getBuildingOfType(String type) {
		for (Building building: buildings) {
			if (building.getType().equals(type)) {
				return building;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	private void defineUnitPriorities() {
		//1. gather all priorities
		//1a. buildings only request for workers
		for (Building building: buildings) {
			if (building.getType().equals("factory")) {
				FactoryManager manager = new FactoryManager();
				manager.setFactory((Factory)building);
				SortedSet<ResourcesRequest> set = manager.analyse();
				requestsQueue.addAll(set);
				
				
			}
		}
		
		
		
//		if (productionCapacity == 0) {//there is no production capacity
//			priority = PlayerPriority.PRODUCTION_CAPACITY;
//		} else if (productionCapacity < 5) {//it takes too long to get resources, build roads 
//			priority = PlayerPriority.ROADS;
//		} else { //only suply resources
//			priority = PlayerPriority.RESOURCES;
//		}
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
			data.capacity+=10;
		}

	}
	
	public void addUsedItem(Item item) {
		usedItems.add(item);
	}

}
