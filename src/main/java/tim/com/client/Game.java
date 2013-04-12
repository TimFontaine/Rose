/**
 * 
 */
package tim.com.client;

import java.util.List;

import tim.game.Map;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class Game {
	
	private Map map;
	
	private List<Player> players;
	
	private Player currectPlayer;

	/**
	 * 
	 */
	public Game() {
		setMap(new Map(50, 50));
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
