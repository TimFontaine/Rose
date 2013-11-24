/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.Game;
import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tim
 *
 */
public class DestroyAction extends GameAction {
	
//	private static final String name="destroyAction";

	/**
	 * @param id
	 * @param gui
	 */
	public DestroyAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui) {
		super(gameActionProp, inGameController, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
