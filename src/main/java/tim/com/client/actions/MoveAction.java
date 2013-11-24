/**
 * 
 */
package tim.com.client.actions;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.data.back.Direction;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tfontaine
 *
 */
public class MoveAction extends GameAction {
	
	public static final String id = "moveAction.";
	
	Direction direction;
	
	/**
	 * 
	 */
	public MoveAction(GameActionProp gameActionProp, InGameController inGameController, GUI gui, String... properties) {
		super(gameActionProp, inGameController, gui);
		this.direction = Direction.valueOf(properties[0]);
	}
	
	public void init(GameActionProp gameActionProp) {
		for (String accelerator : gameActionProp.getAccelerator()){
			String acceleratorSplit[] = accelerator.split("\\.");
			if (acceleratorSplit[0].equals(direction.toString().toLowerCase())){
				setAccelerator(KeyStroke.getKeyStroke(acceleratorSplit[1]));
			}
		}
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
