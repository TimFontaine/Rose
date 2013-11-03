/**
 * 
 */
package tim.com.client.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tim.com.client.actions.RoseAction;
import tim.com.client.controller.ActionManager;
import tim.core.ResourceManager;
import tim.data.back.ClientSpecification;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tim
 *
 */
public class RoseButton extends JButton {
	
	ResourceManager resourceManager = ResourceManager.getInstance();
	
	public RoseButton(GameActionProp actionProp, ActionManager actionManager) {
		RoseAction rose = actionManager.getRoseAction(actionProp.getActionObject());
		setAction(rose);
		Image image = resourceManager.getImage(actionProp.getImage());
		setIcon(new ImageIcon(image));
		if (rose == null) {
			System.out.println("error action " + actionProp.getActionObject()  + "is null!!!");
		}
		
		setSize(50, 50);
		
		repaint();
	}
	
}
