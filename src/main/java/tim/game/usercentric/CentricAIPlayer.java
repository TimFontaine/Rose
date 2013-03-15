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
	private CentricWorker activeUnit;

	/**
	 * @param playerData 
	 * 
	 */
	public CentricAIPlayer(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	
	public void doLogic() {
		for (Unit unit : playerData.getUnits()) {
			activeUnit = (CentricWorker) unit;
			UnitData data = activeUnit.getUnitData();
			activeUnit.move(data.getLocation().x + 1, data.getLocation().y);
		}
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}

	public Unit getActiveUnit() {
		return activeUnit;
	}

	public void setActiveUnit(Unit activeUnit) {
		this.activeUnit = (CentricWorker) activeUnit;
	}

}
