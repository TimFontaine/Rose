/**
 * 
 */
package tim.rose.buttons.actions;

import java.awt.event.ActionEvent;

import tim.com.client.InGameController;
import tim.com.client.RoseAction;
import tim.data.back.TileImprovementType;

/**
 * @author tfontaine
 *
 */
public class TileImprovementAction extends RoseAction {
	
	InGameController inGameController;

	/**
	 * @param id
	 */
	public TileImprovementAction(InGameController inGameController, TileImprovementType tileImprovementType) {
		super(tileImprovementType.getId());
		this.inGameController = inGameController;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("building tileimprovement");
	}

}
