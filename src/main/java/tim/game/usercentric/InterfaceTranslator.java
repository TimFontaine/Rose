/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

import tim.data.back.Direction;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.factory.GameApplicationFactory;

/**
 * TODO this is an AI but driven by a user inteface
 * contains a link to playerdata
 * @author tfontaine
 *
 */
public class InterfaceTranslator {
	
	private PlayerData playerData;
	Unit activeUnit;
	
	Back back;
	
	public InterfaceTranslator() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
	}
	
	/**
	 * humans can make mistakes, check
	 * @param direction
	 * @param amount
	 */
	public void move(Direction direction, int amount) {
		activeUnit = playerData.getUnits().get(0);
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
		back.moveUnit(activeUnit, location.x, location.y);
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}

}
