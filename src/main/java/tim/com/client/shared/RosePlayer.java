/**
 * 
 */
package tim.com.client.shared;

import tim.com.client.Game;
import tim.data.back.Event;
import tim.data.back.Item;
import tim.game.Map;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class RosePlayer implements Player {
	
	private boolean[][] exploredTiles;

	/**
	 * 
	 */
	public RosePlayer(Game game) {
		Map map = game.getMap();
		exploredTiles = new boolean[map.getWidth()][map.getHeight()];
	}
	
	public void explore(Node tile) {
		exploredTiles[tile.getX()][tile.getY()] = true;
	}
	
	public boolean isTileExplored(Node tile) {
		return exploredTiles[tile.getX()][tile.getY()];
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addEvent(tim.data.back.Event)
	 */
	@Override
	public void addEvent(Event event) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see tim.game.Player#initTurn()
	 */
	@Override
	public void initTurn() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUsedItem(tim.data.back.Item)
	 */
	@Override
	public void addUsedItem(Item item) {
		// TODO Auto-generated method stub
		
	}

}
