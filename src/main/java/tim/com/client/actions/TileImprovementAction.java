/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.data.back.TileImprovementType;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tfontaine
 *
 */
public class TileImprovementAction extends GameAction  {
	
	TileImprovementType type;
	GUI gui;

	/**
	 * @param id
	 */
	public TileImprovementAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui, TileImprovementType tileImprovementType) {
		super(gameActionProp, inGameController, gui);
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