/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.data.back.Direction;

/**
 * @author tfontaine
 *
 */
public class MoveAction extends RoseAction {
	
	public static final String id = "moveAction.";
	
	Direction direction;
	InGameController inGameController;

	/**
	 * 
	 */
	public MoveAction(InGameController inGameController, Direction d, GUI gui) {
		super(id + d, gui);
		this.inGameController = inGameController;
		this.direction = d;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		inGameController.moveActiveUnit(direction);
	}

	/* (non-Javadoc)
	 * @see tim.com.client.RoseAction#getId()
	 */
	@Override
	public String getId() {
		return id + direction.toString(); 
	}

}
