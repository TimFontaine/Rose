/**
 * 
 */
package tim.com.client;

import java.util.List;

import tim.data.back.Specification;
import tim.game.Map;
import tim.game.Player;
import tim.namespacetest.types.TerrainType;

/**
 * @author tfontaine
 *
 */
public class Game {
	
	private Map map;
	
	private List<Player> players;
	
	private Player currentPlayer;
	
	private Specification specification;

	/**
	 * 
	 */
	public Game() {
		specification = new Specification();
		MapBuilder mapBuilder = new MapBuilder();
		setMap(mapBuilder.build(specification));
		
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

	public Specification getSpecification() {
		return specification;
	}

}
