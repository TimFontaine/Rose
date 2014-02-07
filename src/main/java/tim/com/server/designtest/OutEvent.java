/**
 * 
 */
package tim.com.server.designtest;

import java.io.Serializable;

import tim.com.client.shared.Player;

/**
 * @author tim
 *
 */
public class OutEvent implements Serializable {
	
	private Player player;
	
	public OutEvent(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
