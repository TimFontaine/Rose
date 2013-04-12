/**
 * 
 */
package tim.com.client;

import tim.data.back.Event;
import tim.data.back.Item;
import tim.data.back.Node;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.data.unit.UnitOrder;
import tim.game.Map;
import tim.game.Player;
import tim.game.usercentric.Actor;

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
	 * @see tim.game.Player#addUnit(tim.data.unit.Unit)
	 */
	@Override
	public void addUnit(Unit unit) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#removeUnit(tim.data.unit.Unit)
	 */
	@Override
	public void removeUnit(Unit unit) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#provideUnit()
	 */
	@Override
	public Unit provideUnit() {
		// TODO Auto-generated method stub
		return null;
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
	 * @see tim.game.Player#orderFinished(tim.data.unit.Unit, tim.data.unit.UnitOrder)
	 */
	@Override
	@Deprecated
	public void orderFinished(Unit source, UnitOrder order) {
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
	 * @see tim.game.Player#addBuilding(tim.data.building.Building)
	 */
	@Override
	public void addBuilding(Building building) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#removeBuilding(tim.data.building.Building)
	 */
	@Override
	public void removeBuilding(Building building) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addUsedItem(tim.data.back.Item)
	 */
	@Override
	@Deprecated
	public void addUsedItem(Item item) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.game.Player#addActor(tim.game.usercentric.Actor)
	 */
	@Override
	public void addActor(Actor actor) {
		// TODO Auto-generated method stub

	}

}
