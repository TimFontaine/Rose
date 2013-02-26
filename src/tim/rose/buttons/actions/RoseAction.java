/**
 * 
 */
package tim.rose.buttons.actions;

import tim.game.Back;

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
		back = Back.getInstance();
	}
	
	public abstract void doAction();

}
