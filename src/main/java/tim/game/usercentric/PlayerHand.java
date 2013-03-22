/**
 * 
 */
package tim.game.usercentric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tim.data.back.Event;
import tim.data.back.Item;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class PlayerHand implements Player {
	
	private List<Unit> units;
	
	private Iterator<Unit> iterator;

	/**
	 * 
	 */
	public PlayerHand() {
		units = new ArrayList<Unit>();
		
	}
	
	/* (non-Javadoc)
	 * @see tim.game.Player#initTurn()
	 */
	@Override
	public void initTurn() {
		iterator= units.iterator();
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUnit(tim.data.unit.Unit)
	 */
	@Override
	public void addUnit(Unit unit) {
		units.add(unit);
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#removeUnit(tim.data.unit.Unit)
	 */
	@Override
	public void removeUnit(Unit unit) {
		units.remove(unit);

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

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addBuilding(tim.data.building.Building)
	 */
	@Override
	public void addBuilding(Building building) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#removeBuilding(tim.data.building.Building)
	 */
	@Override
	public void removeBuilding(Building building) {
		// TODO Auto-generated method stub

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

	/* (non-Javadoc)
	 * @see tim.game.Player#provideUnit()
	 */
	@Override
	public Unit provideUnit() {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

}
