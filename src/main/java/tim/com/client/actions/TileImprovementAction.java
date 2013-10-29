/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
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
		super(tileImprovementType.getId() + "Action", gui);
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