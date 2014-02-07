/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;
import java.util.List;

import tim.com.client.Game;
import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.network.command.client.MoveResultCommand;
import tim.com.client.network.command.server.MoveCommand;
import tim.data.back.Direction;
import tim.namespacetest.types.UnitType;

/**
 * @author tim
 *
 */
public class ServerUnit extends Unit {

	/**
	 * @param unitType
	 * @param owner
	 */
	public ServerUnit(UnitType unitType, Player owner, Game game) {
		super(unitType, owner, game);
	}

	/**
	 * @param direction
	 * @param eventList 
	 */
	public void move(Direction direction, List<GameEventCommand> eventList) {
		Point newLocation = super.translateMove(direction);
		boolean legal = testMoveLegal(newLocation);
		
		MoveResultCommand moveResultCommand = new MoveResultCommand();
		eventList.add(moveResultCommand);
	}

	/**
	 * @param newLocation
	 * @return
	 */
	private boolean testMoveLegal(Point newLocation) {
		Point mapBounds = game.getMap().getBounderies();
		if (newLocation.x < 0 && newLocation.y < 0 
				&& newLocation.x >= mapBounds.x && newLocation.y >= mapBounds.y){
			return false;
		}
		
		/**
		 * TODO add test for terrainType 
		 **/
		return true;
	}

}
