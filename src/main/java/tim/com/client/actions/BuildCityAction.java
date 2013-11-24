/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tfontaine
 *
 */
public class BuildCityAction extends GameAction {
	
	private String item;
//	/**
//	 * 
//	 */
//	public BuildAction(String item) {
//		this.item = item;
//	}

	/**
	 * @param inGameController
	 */
	public BuildCityAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui) {
		super(gameActionProp, inGameController, gui);
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
