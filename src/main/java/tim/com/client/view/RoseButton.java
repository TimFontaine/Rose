/**
 * 
 */
package tim.com.client.view;

import javax.swing.JButton;

import tim.com.client.actions.RoseAction;
import tim.com.client.controller.ActionManager;

/**
 * @author tim
 *
 */
public class RoseButton extends JButton {
	
	public RoseButton(String name, ActionManager actionManager) {
		RoseAction rose = actionManager.getRoseAction(name);
		setAction(rose);
		
		setText("dummy button");
	}
	
}
