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
	public RoseAction() {
		setAccelerator(KeyStroke.getKeyStroke("LEFT"));
	}
	
	public abstract String getId();

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
