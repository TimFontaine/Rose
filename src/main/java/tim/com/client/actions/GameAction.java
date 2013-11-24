/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tim
 *
 */
public abstract class GameAction extends RoseAction {
	
	protected InGameController inGameController;
	
	/**
	 * @param id
	 * @param gui
	 */
	public GameAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui) {
		super(gameActionProp, gui);
		this.inGameController = inGameController;
	}

	
}
