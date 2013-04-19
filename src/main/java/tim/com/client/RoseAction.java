/**
 * 
 */
package tim.com.client;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.w3c.dom.views.AbstractView;

/**
 * @author tfontaine
 *
 */
public abstract class RoseAction extends AbstractAction {
	
	

	/**
	 * 
	 */
	public RoseAction(String id) {
		String acceleratorKey = id + ".accelerator";
		String accelerator = Messages.message(acceleratorKey);
		setAccelerator(KeyStroke.getKeyStroke(accelerator));
		putValue("id", id);
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

}
