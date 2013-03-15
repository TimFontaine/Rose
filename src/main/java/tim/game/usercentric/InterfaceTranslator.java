/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

import tim.data.back.Direction;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.BasicPlayer;
import tim.game.factory.GameApplicationFactory;

/**
 * TODO this is an AI but driven by a user inteface
 * contains a link to playerdata
 * @author tfontaine
 *
 */
public class InterfaceTranslator extends BasicPlayer {
	
	private PlayerData playerData;
	CentricWorker activeUnit;
	CentricWorker centricWorker;
	
	Back back;
	
	public InterfaceTranslator(PlayerData playerData) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		this.playerData = playerData;
	}
	
	public void doLogic() {
		for (Unit unit : playerData.getUnits()) {
			activeUnit = (CentricWorker) unit;
		}
	}
	
	/**
	 * humans can make mistakes, check
	 * @param direction
	 * @param amount
	 */
	public void move(Direction direction, int amount) {
		Point location = activeUnit.getLocation();
		switch (direction) {
		case DOWN:
			location.y+=amount;
			break;
		case UP:
			location.y-=amount;
			break;
		case LEFT:
			location.x-=amount;
			break;
		case RIGHT:
			location.x+=amount;
			break;
		default:
			break;
		}
		activeUnit.move(location.x, location.y);
	}
	
	public void specialAction(SpecialAction action) {
		activeUnit.specialAction(action);
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}

}
