/**
 * 
 */
package tim.game.usercentric;

import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.BasicPlayer;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class CentricAIPlayer extends BasicPlayer {
	
	private Actor activeUnit;

	/**
	 * 
	 */
	public CentricAIPlayer() {
		this.playerData = playerData;
	}
	
	
	public void doLogic() {
		for (Actor actor : playerData.getActors()) {
			activeUnit = actor;
//			UnitData data = activeUnit.getUnitData();
//			activeUnit.move(data.getLocation().x + 1, data.getLocation().y);
		}
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		Back back = applicationFactory.getBack();
		back.nextPlayer();
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}

//	public Unit getActiveUnit() {
//		return activeUnit;
//	}
//
//	public void setActiveUnit(Unit activeUnit) {
//		this.activeUnit = (CentricWorker) activeUnit;
//	}

}
