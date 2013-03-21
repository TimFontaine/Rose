/**
 * 
 */
package tim.data.back;

import tim.game.Player;


/**
 * @author tfontaine
 *
 */
public class Item extends MapItem {
	
	private Player player;

	/**
	 * @param name
	 */
	public Item(String name) {
		super(name);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	

}
