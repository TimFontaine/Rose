/**
 * 
 */
package tim.com.client;

import java.util.List;

import tim.data.back.GameSpecification;
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
	
	private GameSpecification gameSpecification;

	/**
	 * 
	 */
	public Game(GameSpecification gameSpecification) {
		this.gameSpecification = gameSpecification;
		MapBuilder mapBuilder = new MapBuilder();
		setMap(mapBuilder.build(gameSpecification));
		
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

	public void setGameSpecification(GameSpecification gameSpecification) {
		this.gameSpecification = gameSpecification;
	}
	
	public GameSpecification getGameSpecification() {
		return gameSpecification;
	}


}
