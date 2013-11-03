/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;

/**
 * @author tim
 *
 */
public class DestroyAction extends RoseAction {
	
	private static final String name="destroyAction";

	/**
	 * @param id
	 * @param gui
	 */
	public DestroyAction(String id, GUI gui) {
		super(id, gui);
	}

	/**
	 * @param gui
	 */
	public DestroyAction(GUI gui) {
		super(name, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
