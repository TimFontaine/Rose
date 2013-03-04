/**
 * 
 */
package tim.rose.buttons.actions;

import tim.game.Back;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public abstract class RoseAction {

	Back back;
	
	/**
	 * 
	 */
	public RoseAction() {
		back = GameApplicationFactory.getInstance().getBack();
	}
	
	public abstract void doAction();

}
