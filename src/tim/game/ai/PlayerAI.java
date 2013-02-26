/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.List;

import tim.data.back.Building;
import tim.data.back.Event;
import tim.data.back.EventCode;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.back.RoseObject;
import tim.data.back.Thing;
import tim.data.unit.OilTruck;
import tim.data.unit.Truck;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.data.unit.UnitState;
import tim.game.Back;
import tim.game.Player;
import tim.pathfinding.Dijkstra;

/**
 * @author tfontaine
 *
 */
public class PlayerAI extends RoseObject implements Player {
	
	private List<Unit> units;
	private List<Building> buildings;
	private List<Event> events;

	private List<Unit> activeUnits;
	private Back back;
	/**
	 * 
	 */
	public PlayerAI(String name) {
		super(name);
		units = new ArrayList<Unit>();
		activeUnits = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
		events = new ArrayList<Event>();
		back = Back.getInstance();
	}
	
	public void doLogic() {
		//handle events
		for (Event event: events) {
			if (event.getCode() == EventCode.OIL_EMPTY) {
				//is there an oil truck available ?
				Path path = back.findNearestObject((Thing) event.getSource(), "oilTruck");
				path.reversePath();
				Node first = path.first();
				OilTruck unit = (OilTruck) first.getFirstUnitOfType("oilTruck");
				UnitOrder order = new UnitOrder(path, (Unit)event.getSource(), EventCode.GIVE_OIL);
				unit.setOrder(order);
				unit.initJob();
				unit.setState(UnitState.ACTIVE);
			}
		}
		events.clear();
		
		for (Building building: buildings) {
			building.doLogic();
		}
		
		//handle units
		for (Unit unit: activeUnits) {
			if (unit.getState() == UnitState.IDLE) {
				findTaskForUnit(unit);
			} else if (unit.getState() == UnitState.ACTIVE) {
				unit.doLogic();
			}
			if (unit.getState() == UnitState.TURN_FINISHED) {
//				activeUnits.remove(unit);
			}
		}
		
		if (activeUnits.isEmpty()) {
			Event event = new Event();
			event.setSource(this);
			event.setCode(EventCode.TURN_FINISHED);
			event.setDescription("step finished");
			back.getEvents().add(event);
		}
		
	}

	private void findTaskForUnit(Unit unit) {
		unit.initJob();
		if (unit.getJob() != null) {
			unit.setState(UnitState.ACTIVE);
		}
	}

	@Override
	public void addUnit(Unit unit) {
		units.add(unit);
	}
	
	@Override
	public void addBuilding(Building building) {
		buildings.add(building);
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	@Override
	public void addEvent(Event event) {
		events.add(event);
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	@Override
	public void orderFinished(Unit source, UnitOrder order) {
		
	}

	@Override
	public void initTurn() {
		System.out.println("init turn");
		activeUnits.clear();
		for (Unit unit: units) {
			activeUnits.add(unit);
			unit.setState(unit.getDefaultState());
			unit.setMoves(Unit.MAX_MOVES);
		}
	}

}
