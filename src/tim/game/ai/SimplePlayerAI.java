/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


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

	/**
	 * 
	 */
	public SimplePlayerAI(String name) {
		super(name);
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		playerOrders = new LinkedList<PlayerOrder>();
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
		definePriorities();
		if (priority == PlayerPriority.PRODUCTION_CAPACITY) {
			ProductionPlanner planner = new ProductionPlanner();
			PlayerOrder order = planner.doAction(); 
			playerOrders.add(order);
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
		
		for (Unit unit: units) {
			if (unit.getState() == UnitState.IDLE) {
				if (!playerOrders.isEmpty()) {
					PlayerOrder order = playerOrders.poll();
					if (unit.getType().equals(order.getProcessorType())) {
						((Worker)unit).setPlayerOrder(order);
					}
				}
			}
			unit.doLogic();
		}

	}

	/**
	 * 
	 */
	private void definePriorities() {
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
