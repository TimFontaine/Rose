/**
 * 
 */
package tim.rose.buttons.actions;

import tim.data.back.Path;

/**
 * @author tfontaine
 *
 */
public class CalcpathAction extends RoseAction {

	/**
	 * 
	 */
	public CalcpathAction() {
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
		Path path = back.calcPath(back.getPlayer());
		back.addPath(path);
	}

}
