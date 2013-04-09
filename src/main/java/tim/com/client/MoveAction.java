/**
 * 
 */
package tim.com.client;

import java.awt.event.ActionEvent;

import tim.data.back.Direction;

/**
 * @author tfontaine
 *
 */
public class MoveAction extends RoseAction {
	
	public static final String id = "moveAction.";
	
	Direction direction;

	/**
	 * 
	 */
	public MoveAction(Direction d) {
		this.direction = d;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("move launched");
	}

	/* (non-Javadoc)
	 * @see tim.com.client.RoseAction#getId()
	 */
	@Override
	public String getId() {
		return id + direction.toString(); 
	}

}
