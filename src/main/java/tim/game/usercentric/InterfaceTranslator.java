/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import javax.swing.SwingUtilities;

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
 * user player differs from simple player that they do validation
 * @author tfontaine
 *
 */
public class InterfaceTranslator extends BasicPlayer {
	
	ResourceInfo resourceInfo;
	
	Actor activeItem;
	Building selectedBuilding;//human player only
	
	private boolean active;
	
	public enum Selection {
		UNIT,
		BUILDING,
		NONE;
	}
	
	Back back;
	
	public InterfaceTranslator() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		resourceInfo = applicationFactory.getResourceInfo();
	}
	
	public void initTurn() {
		active = true;
		for (Building building : buildings) {
			building.doLogic();
		}
		activeItem = playerData.getActors().get(0);
	}
	
	public void doLogic() {
		for (Actor actor : playerData.getActors()) {
			activeItem = actor;
		}
	}
	
	/**
	 * humans can make mistakes, check
	 * humans just move, translate in move or attack
	 * @param direction
	 * @param amount
	 */
	public void move(Direction direction, int amount) {
		//remove later
//		if (!active || activeItem == null) {
//			return;
//		}
//		
		Point newLocation = translateMove(direction);
		boolean legal = testMoveLegal(newLocation);
		if (!legal) {
			return;
		}
		
//		boolean containsEnemy = back.containsEnemy(newLocation);
//		if (activeItem.canAttack() && containsEnemy) {
//			activeItem.attack(newLocation);
//		} else {
			back.moveUnit(newLocation.x, newLocation.y);
//		}
	}
	
	/**
	 * @param newLocation
	 * @return
	 */
	private boolean testMoveLegal(Point location) {
		if (location.x < 0 || location.y < 0) {
			return false;
		}
		Point bounderies = back.getBounderies();
		if (location.x >= bounderies.x || location.y >= bounderies.y) {
			return false;
		}
		return true;
	}

	/**
	 * move to bridge pattern
	 */
	private Point translateMove(Direction direction) {
		Unit unit = back.getActiveUnit();
		Point newLocation = new Point(unit.getLocation());
		switch (direction) {
		case DOWN:
			newLocation.y++;
			break;
		case UP:
			newLocation.y--;
			break;
		case LEFT:
			newLocation.x--;
			break;
		case RIGHT:
			newLocation.x++;
			break;
		default:
			break;
		}
		
		return newLocation;
	}
	
	
	
	/**
	 * check destination is blocked
	 * @param destination
	 */
	public void gotoLocation(Point destination) {
		back.gotoLocation(destination);
	}
	
	public void specialAction(SpecialAction action) {
		//remove later
		if (activeItem != null) {//assert unit is selected
			activeItem.specialAction(action);
		} else if (selectedBuilding != null) {
			selectedBuilding.specialAction(action);
		}
	}
	
	/**
	 * TODO return building data with link and blalbla
	 * 
	 */
	public void addBuilding(String type) {
		back.addBuilding(type);
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
			back.switchSelectedUnit(unit);
			return Selection.UNIT;
		}else if (node.containsItem()) {
			selectedBuilding = (Building) node.getItem();
			return Selection.BUILDING;
		} else {
			activeItem = null;
			return Selection.NONE;
		}
	}
	
	public List<String> getPossibleUnitActions() {
		return resourceInfo.getUnitActions(back.getActiveUnit().getType());
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

	/**
	 * 
	 */
	public void nextPlayer() {
		active = false;
		back.nextPlayer();
	}

}
