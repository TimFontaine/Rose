/**
 * 
 */
package tim.rose.buttons.actions;

import tim.game.Back;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.InterfaceTranslator;

/**
 * @author tfontaine
 *
 */
public abstract class RoseAction {

	Back back;
	InterfaceTranslator translator;
	
	/**
	 * 
	 */
	public RoseAction() {
		back = GameApplicationFactory.getInstance().getBack();
		translator = back.getTrans();
		
	}
	
	public abstract void doAction();

}
