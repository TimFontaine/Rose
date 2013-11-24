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
public class DummyAction extends GameAction {
	
	private static final String id = "dummy";

	/**
	 * @param id
	 * @param gui
	 */
	public DummyAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui) {
		super(gameActionProp, inGameController, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("dummy action called");

	}

}
