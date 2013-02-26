/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.List;

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
	
	
	PlayerPriority priority;

	/**
	 * 
	 */
	public SimplePlayerAI(String name) {
		super(name);
		units = new ArrayList<Unit>();
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
		definePriorities();
		PlayerOrder order = null ;
		if (priority == PlayerPriority.PRODUCTION) {
			ProductionPlanner planner = new ProductionPlanner();
			order = planner.doAction(); 
		}
		
		for (Unit unit: units) {
			if (unit.getState() == UnitState.IDLE) {
				if (unit.getType().equals(order.getProcessorType())) {
					((Worker)unit).setPlayerOrder(order);
				}
			}
			unit.doLogic();
		}

	}

	/**
	 * 
	 */
	private void definePriorities() {
		priority = PlayerPriority.PRODUCTION;
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
		// TODO Auto-generated method stub

	}

}
