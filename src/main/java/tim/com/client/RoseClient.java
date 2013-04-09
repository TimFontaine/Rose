/**
 * 
 */
package tim.com.client;

import tim.core.InputManager;

/**
 * @author tfontaine
 *
 */
public class RoseClient {
	
	private ActionManager actionManager;

	/**
	 * 
	 */
	public RoseClient() {	
		GUI gui = new GUI(this);
		
actionManager = new ActionManager();
		
		actionManager.initializeActions();
		gui.startGUI(GUI.determineFullScreenSize());
		
		
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

}
