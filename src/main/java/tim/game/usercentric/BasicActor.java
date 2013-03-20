/**
 * 
 */
package tim.game.usercentric;

import tim.data.back.Node;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.Player;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * this is a template pattern
 */
public abstract class BasicActor {
	
	protected Unit unit;
	protected Back back;
	

	/**
	 * 
	 */
	public BasicActor(Unit unit) {
		this.unit = unit;
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public boolean containsEnemy(Node node) {
		Player current = unit.getPlayer();
		if (node.containsMapItem()) {
			if (node.containsUnit()) {
				Unit unit = node.getUnits().get(0);
				if (current != unit.getPlayer()) {
					return true;
				}
			}
		}
		return false;
	}
	
}
