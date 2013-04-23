/**
 * 
 */
package tim.rose.buttons.actions;

import java.awt.event.ActionEvent;

import tim.com.client.GUI;
import tim.com.client.InGameController;
import tim.com.client.RoseAction;
import tim.data.back.TileImprovementType;

/**
 * @author tfontaine
 *
 */
public class TileImprovementAction extends RoseAction {
	
	InGameController inGameController;
	TileImprovementType type;
	GUI gui;

	/**
	 * @param id
	 */
	public TileImprovementAction(InGameController inGameController, GUI gui, TileImprovementType tileImprovementType) {
		super(tileImprovementType.getId() + "Action");
		this.inGameController = inGameController;
		this.type = tileImprovementType;
		this.gui = gui;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("building tileimprovement");
		inGameController.buildTileImprovement(type);
	}

}
