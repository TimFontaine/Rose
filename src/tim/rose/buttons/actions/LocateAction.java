/**
 * 
 */
package tim.rose.buttons.actions;

import tim.data.back.Path;

/**
 * @author tfontaine
 *
 */
public class LocateAction extends RoseAction {
	
	private String itemName;

	/**
	 * 
	 */
	public LocateAction() {
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
//		Path path = back.findNearestObject(back.getPlayer(), itemName);
//		back.addPath(path);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
