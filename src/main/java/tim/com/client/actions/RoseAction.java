/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.w3c.dom.views.AbstractView;

import tim.com.client.Messages;
import tim.com.client.controller.GUI;

/**
 * @author tfontaine
 *
 */
public abstract class RoseAction extends AbstractAction {
	
	protected GUI gui;

	/**
	 * 
	 */
	public RoseAction(String id, GUI gui) {
		String acceleratorKey = id + ".accelerator";
		String accelerator = Messages.message(acceleratorKey);
		setAccelerator(KeyStroke.getKeyStroke(accelerator));
		putValue("id", id);
		this.gui = gui;
	}

	public String getId() {
		return (String) getValue("id");
	}

	/**
	 * @return
	 */
	public void setAccelerator(KeyStroke accelerator) {
		putValue(ACCELERATOR_KEY, accelerator);
	}
	
	public KeyStroke getAccelerator() {
		return (KeyStroke) getValue(ACCELERATOR_KEY);
	}

	/**
	 * 
	 */
	public void update() {		
	}

}
