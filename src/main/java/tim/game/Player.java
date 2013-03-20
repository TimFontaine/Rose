/**
 * 
 */
package tim.game;

import tim.data.back.Event;
import tim.data.back.Item;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.game.usercentric.Actor;

/**
 * @author tfontaine
 *
 */
public interface Player {

	public void addUnit(Unit unit);

	public void doLogic();
	
	public void addEvent(Event event);

	public void orderFinished(Unit source, UnitOrder order);

	public void initTurn();

	void addBuilding(Building building);
	
	public void addUsedItem(Item item);

	/**
	 * @param unit
	 */
	public void addActor(Actor actor);
}
