/**
 * 
 */
package tim.data.back;

import java.io.Serializable;

import tim.game.Back;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public abstract class Thing extends MapItem implements Serializable {

	protected Back back;
	protected Player player;
	
	public Thing(String name) {
		super(name);
		back = Back.getInstance();
	}

	public abstract void doLogic();
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
}
