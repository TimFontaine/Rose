/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.List;

import tim.data.back.Event;
import tim.data.back.Item;
import tim.data.back.RoseObject;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.game.Player;
import tim.game.usercentric.Actor;

/**
 * @author tfontaine
 *
 */
public class BasicPlayer extends RoseObject implements Player {
	
	protected List<Unit> units; 
	protected List<Building> buildings;

	/**
	 * 
	 */
	public BasicPlayer() {
		super("basicPlayer");
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
				
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
		// TODO Auto-generated method stub

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
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUsedItem(tim.data.back.Item)
	 */
	@Override
	public void addUsedItem(Item item) {
		// TODO Auto-generated method stub

	}

	
	/* (non-Javadoc)
	 * @see tim.game.Player#addActor(tim.game.usercentric.Actor)
	 */
	@Override
	public void addActor(Actor actor) {
		// TODO Auto-generated method stub
		
	}

}
