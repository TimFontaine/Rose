/**
 * 
 */
package tim.rose.buttons.actions;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import tim.com.client.InGameController;
import tim.com.client.RoseAction;
import tim.core.ScreenUtils;
import tim.data.front.MouseInfoItem;
import tim.data.front.MouseState;
import tim.game.usercentric.SpecialAction;

/**
 * @author tfontaine
 *
 */
public class BuildAction extends RoseAction {
	
	public static final String id = "buildCityAction";
	
	private String item;
	private InGameController inGameController;

//	/**
//	 * 
//	 */
//	public BuildAction(String item) {
//		this.item = item;
//	}

	/**
	 * @param inGameController
	 */
	public BuildAction(InGameController inGameController) {
		super(id);
		this.inGameController = inGameController;
	}

//	/* (non-Javadoc)
//	 * @see tim.rose.buttons.actions.RoseAction#doAction()
//	 */
//	@Override
//	public void doAction() {
////		MouseInfoItem.mouseState = MouseState.SELECTED;
////		MouseInfoItem.item = item;
//////		ScreenUtils.getInstance().changeMouseCursor(item);
////		SpecialAction action = SpecialAction.BUILD;
////		action.setData(item);
////		translator.specialAction(action);
//		translator.addBuilding(item);
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("building city");
		inGameController.buildCity();
	}

}
