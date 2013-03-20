/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import tim.data.back.Direction;
import tim.data.back.Node;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.BasicPlayer;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.ai.job.Job.JobType;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.UnitData.UnitState;

/**
 * TODO this is an AI but driven by a user inteface
 * contains a link to playerdata
 * 
 * -split in usertranslator and buildingtranslator
 * @author tfontaine
 *
 */
public class InterfaceTranslator extends BasicPlayer {
	
	ResourceInfo resourceInfo;
	
	private PlayerData playerData;
	Actor activeItem;
	Building selectedBuilding;//human player only
	
	public enum Selection {
		UNIT,
		BUILDING,
		NONE;
	}
	
	Back back;
	
	public InterfaceTranslator(PlayerData playerData) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		resourceInfo = applicationFactory.getResourceInfo();
		this.playerData = playerData;
	}
	
	public void initTurn() {
		for (Building building : buildings) {
			building.doLogic();
		}
	}
	
	public void doLogic() {
		for (Actor actor : playerData.getActors()) {
			activeItem = actor;
		}
	}
	
	/**
	 * humans can make mistakes, check
	 * @param direction
	 * @param amount
	 */
	public void move(Direction direction, int amount) {
		//remove later
		if (activeItem == null) {
			return;
		}
		Point location = activeItem.getUnit().getLocation();
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
		activeItem.move(location.x, location.y);
	}
	
	public void gotoLocation(Point destination) {
		ComplexOrder order = new ComplexOrder();
		Job job = new GotoJob(activeItem.getUnit(), destination);
		job.setType(JobType.PATH);
		order.addJob(job);
		activeItem.setComplexOrder(order);
	}
	
	public void specialAction(SpecialAction action) {
		//remove later
		if (activeItem != null) {//assert unit is selected
			activeItem.specialAction(action);
		} else if (selectedBuilding != null) {
			selectedBuilding.specialAction(action);
		}
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
		activeItem = null;
		selectedBuilding = null;
		Node node = back.getNode(location.x, location.y);
		if (node.containsUnit()) {
			Unit unit = node.getUnits().get(0);
			activeItem = unit.getActor();
			return Selection.UNIT;
		}else if (node.containsItem()) {
			selectedBuilding = (Building) node.getItem();
			return Selection.BUILDING;
		} else {
			activeItem = null;
			return Selection.NONE;
		}
	}
	
	public void addActor(Actor actor) {
		
	}
	
	public List<String> getPossibleUnitActions() {
		return resourceInfo.getUnitActions(activeItem.getUnit().getType());
	}

	public Actor getActiveUnit() {
		return activeItem;
	}

	public void setActiveUnit(WorkerActor activeUnit) {
		this.activeItem = activeUnit;
	}

	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}

}
