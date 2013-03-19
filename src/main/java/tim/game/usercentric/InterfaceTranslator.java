/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

import tim.data.back.Direction;
import tim.data.back.Node;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.BasicPlayer;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.ai.job.Job.JobType;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.UnitData.UnitState;

/**
 * TODO this is an AI but driven by a user inteface
 * contains a link to playerdata
 * @author tfontaine
 *
 */
public class InterfaceTranslator extends BasicPlayer {
	
	
	private PlayerData playerData;
	CentricWorker activeUnit;
	Building selectedBuilding;//human player only
	CentricWorker centricWorker;
	
	public enum Selection {
		UNIT,
		BUILDING,
		NONE;
	}
	
	Back back;
	
	public InterfaceTranslator(PlayerData playerData) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		this.playerData = playerData;
	}
	
	public void initTurn() {
		for (Building building : buildings) {
			building.doLogic();
		}
	}
	
	public void doLogic() {
		for (Unit unit : playerData.getUnits()) {
			activeUnit = (CentricWorker) unit;
			if (activeUnit.getUnitData().getState() == UnitState.MULTI) {
				activeUnit.doLogic();
			}
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
	
	public void build(String itemName) {
		selectedBuilding.giveOrder(itemName);
	}
	
	public void gotoLocation(Point destination) {
		ComplexOrder order = new ComplexOrder();
		Job job = new GotoJob(activeUnit, destination);
		job.setType(JobType.PATH);
		order.addJob(job);
		activeUnit.setComplexOrder(order);
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

	/**
	 * @param destination
	 */
	public Selection selectScreenItem(Point location) {
		Node node = back.getNode(location.x, location.y);
		if (node.containsUnit()) {
			Unit unit = node.getUnits().get(0);
			activeUnit = (CentricWorker) unit;
			return Selection.UNIT;
		}else if (node.containsItem()) {
			selectedBuilding = (Building) node.getItem();
			return Selection.BUILDING;
		} else {
			activeUnit = null;
			return Selection.NONE;
		}
	}

	public CentricWorker getActiveUnit() {
		return activeUnit;
	}

	public void setActiveUnit(CentricWorker activeUnit) {
		this.activeUnit = activeUnit;
	}

	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}

}
