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
public class SkipAction extends RoseAction {

	private static final String name  = "skipAction";
	
	/**
	 * @param id
	 * @param gui
	 */
	public SkipAction(GUI gui) {
		super(name, gui);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
