/**
 * 
 */
package tim.game;

import tim.data.back.Event;
import tim.data.back.Item;

/**
 * @author tfontaine
 *
 */
public interface Player {

	public void doLogic();
	
	public void addEvent(Event event);

	public void initTurn();

	@Deprecated
	public void addUsedItem(Item item);

}
