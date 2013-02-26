/**
 * 
 */
package tim.rose.buttons.actions;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import tim.core.ScreenUtils;
import tim.data.front.MouseInfoItem;
import tim.data.front.MouseState;

/**
 * @author tfontaine
 *
 */
public class BuildAction extends RoseAction {
	
	private String item;

	/**
	 * 
	 */
	public BuildAction(String item) {
		this.item = item;
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
		MouseInfoItem.mouseState = MouseState.SELECTED;
		MouseInfoItem.item = item;
		ScreenUtils.getInstance().changeMouseCursor(item);
	}
	

}
