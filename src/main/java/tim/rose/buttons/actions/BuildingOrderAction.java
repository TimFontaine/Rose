/**
 * 
 */
package tim.rose.buttons.actions;

import tim.core.GameAction;
import tim.game.usercentric.SpecialAction;

/**
 * @author tfontaine
 *
 */
public class BuildingOrderAction extends RoseAction {
	
	String item;

	/**
	 * 
	 */
	public BuildingOrderAction(String item) {
		this.item = item;
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
		SpecialAction action = SpecialAction.PRODUCE;
		action.setData(item);
		translator.specialAction(action);
	}

}
