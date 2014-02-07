/**
 * 
 */
package tim.com.client.shared;

import java.util.HashMap;
import java.util.List;

import tim.com.client.Game;
import tim.data.back.Event;
import tim.data.back.Item;
import tim.game.Map;

/**
 * @author tfontaine
 *
 */
public class RosePlayer implements Player {
	
	private boolean[][] exploredTiles;
	
	private java.util.Map<String, Unit> units;
	
	private Game game;

	/**
	 * 
	 */
	public RosePlayer(Game game) {
		this.game = game;
		
		this.units = new HashMap<String, Unit>();
	}
	
	public void init() {
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

	/* (non-Javadoc)
	 * @see tim.game.Player#getUnits()
	 */
	@Override
	public List<Unit> getUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see tim.game.Player#getUnitById(java.lang.String)
	 */
	@Override
	public Unit getUnitById(String unitId) {
		return units.get(unitId);
		
	}

	/* (non-Javadoc)
	 * @see tim.com.client.shared.Player#addUnit(tim.com.client.shared.Unit)
	 */
	@Override
	public void addUnit(Unit unit) {
		units.put(unit.getId(), unit);
	}
	
	

}
