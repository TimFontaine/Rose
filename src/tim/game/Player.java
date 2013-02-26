/**
 * 
 */
package tim.game;

import tim.data.back.Building;
import tim.data.back.Event;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;

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
}
