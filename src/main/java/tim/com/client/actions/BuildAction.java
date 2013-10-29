/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;

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
	public BuildAction(InGameController inGameController, GUI gui) {
		super(id, gui);
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
