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
public class SkipAction extends GameAction {

	
//	/**
//	 * @param id
//	 * @param gui
//	 */
//	public SkipAction(GUI gui) {
//		super(name, gui);
//	}

	/**
	 * @param id
	 * @param gui
	 */
	public SkipAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui) {
		super(gameActionProp, inGameController, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
