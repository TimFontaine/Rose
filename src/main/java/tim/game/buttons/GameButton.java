/**
 * 
 */
package tim.game.buttons;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import tim.core.ResourceManager;
import tim.game.Back;
import tim.rose.buttons.actions.RoseAction;

/**
 * @author tfontaine
 *
 */
public class GameButton extends JButton {

	ResourceManager resourceManager;
	private RoseAction roseAction;
	
	/**
	 * 
	 */
	public GameButton(ActionListener listener) {
		resourceManager = ResourceManager.getInstance();
		setFocusable(false);
		addActionListener(listener);
	}

	/**
	 * @param arg0
	 */
	public GameButton(Icon arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public GameButton(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public GameButton(Action arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public GameButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	
	public void doAction() {}
	
	public void setIcon(String iconName) {
		setIcon(new ImageIcon(resourceManager.getImage(iconName)));
	}
	
	protected void changeMouseCursor(String name) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image im = resourceManager.getImage(name);
		Cursor cursor = toolkit.createCustomCursor(im, new Point(0,0), "my ima");
		getRootPane().setCursor(cursor);
	}

	public RoseAction getRoseAction() {
		return roseAction;
	}

	public void setRoseAction(RoseAction roseAction) {
		this.roseAction = roseAction;
	}
}
