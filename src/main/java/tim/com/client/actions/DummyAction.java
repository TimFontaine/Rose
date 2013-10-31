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
public class DummyAction extends RoseAction {
	
	private static final String id = "dummy";

	/**
	 * @param id
	 * @param gui
	 */
	public DummyAction(GUI gui) {
		super(id, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("dummy action called");

	}

}
