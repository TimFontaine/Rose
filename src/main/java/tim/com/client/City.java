/**
 * 
 */
package tim.com.client;

import tim.data.back.Node;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class City {
	
	private Node tile;
	private Player owner;

	/**
	 * @param player
	 * @param tile
	 */
	public City(Player player, Node tile) {
		this.owner = player;
		this.tile = tile;
	}
	
	public void placeCity() {
		tile.setCity(this);
	}

	/**
	 * 
	 */
	public void contstructBuilding(String buildingName) {
		System.out.println("city has order to build:" + buildingName);
	}

}
