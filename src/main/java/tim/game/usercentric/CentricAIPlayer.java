/**
 * 
 */
package tim.game.usercentric;

import tim.data.unit.Unit;
import tim.game.ai.BasicPlayer;

/**
 * @author tfontaine
 *
 */
public class CentricAIPlayer extends BasicPlayer {
	
	private PlayerData playerData;
	private Actor activeUnit;

	/**
	 * @param playerData 
	 * 
	 */
	public CentricAIPlayer(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	
	public void doLogic() {
		for (Actor actor : playerData.getActors()) {
			activeUnit = actor;
//			UnitData data = activeUnit.getUnitData();
//			activeUnit.move(data.getLocation().x + 1, data.getLocation().y);
		}
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
