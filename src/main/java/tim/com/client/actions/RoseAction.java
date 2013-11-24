/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.w3c.dom.views.AbstractView;

import tim.com.client.Messages;
import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.data.back.ClientSpecification;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tfontaine
 *
 */
public abstract class RoseAction extends AbstractAction {
	
	protected GUI gui;

	/**
	 * 
	 */
	public RoseAction(String id,ClientSpecification clientSpecification, GUI gui) {
		String acceleratorKey = id + ".accelerator";
		String accelerator = Messages.message(acceleratorKey);
		setAccelerator(KeyStroke.getKeyStroke(accelerator));
		putValue("id", id);
		this.gui = gui;
	}
	
	public RoseAction(GameActionProp gameActionProp, GUI gui) {
		putValue("id", gameActionProp.getActionObject());
		if (gameActionProp.getAccelerator().size() == 1) {
			setAccelerator(KeyStroke.getKeyStroke(gameActionProp.getAccelerator().get(0)));
		} else {
			setAccelerator(loadAccelerator(gameActionProp));
		}
		this.gui = gui;
	}
	
	/**
	 * more as 1 accelerator, find the correct one
	 * @param gameActionProp
	 * @return
	 */
	protected KeyStroke loadAccelerator(GameActionProp gameActionProp) {return null;}
	

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

	/**
	 * 
	 */
	public void init(GameActionProp gameActionProp) {
	}

}
