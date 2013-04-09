/**
 * 
 */
package tim.com.client;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JDesktopPane;

/**
 * @author tfontaine
 *
 */
public class Canvas extends JDesktopPane {
	
	RoseClient roseClient;

	/**
	 * 
	 */
	public Canvas(RoseClient roseClient, Dimension size) {
		
		this.roseClient = roseClient;
		setLocation(0, 0);
        setSize(size);

        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(null);
        
        createKeyBindings();
        
	}
	
	private void createKeyBindings() {
		for (RoseAction action : roseClient.getActionManager().getActions().values()) {
			getInputMap().put(action.getAccelerator(), action.getId());
			getActionMap().put(action.getId(), action);
		}
	}

}
