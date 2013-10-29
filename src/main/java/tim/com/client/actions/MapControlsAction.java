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
public class MapControlsAction extends RoseAction {
	
	private static final String ID = "mapControlAction"; 
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/**
	 * @param id
	 */
	public MapControlsAction(GUI gui) {
		super(ID, gui);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.showMapControls();
	}
	
	public void update() {
		gui.showMapControls();
	}

}
