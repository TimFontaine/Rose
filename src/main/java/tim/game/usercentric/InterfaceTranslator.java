/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import javax.swing.SwingUtilities;

import tim.data.back.Direction;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.data.back.TileInfo;
import tim.data.back.TileInfo.Selection;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.RoseRules;
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
 * 
 * user/building actions control in this layer
 * @author tfontaine
 *
 */
public class InterfaceTranslator extends BasicPlayer {
	
	ResourceInfo resourceInfo;
	
	Building selectedBuilding;//human player only
	
	private boolean active;
	
	private Selection selected;
	
	Back back;
	RoseRules roseRules;
	
	public InterfaceTranslator() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		roseRules = applicationFactory.getRoseRules();
		resourceInfo = applicationFactory.getResourceInfo();
		selected = Selection.NONE;
	}
	
	public void initTurn() {
		active = true;
		for (Building building : buildings) {
			building.doLogic();
		}
	}
	
	public void doLogic() {
		for (Actor actor : playerData.getActors()) {
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
		
		boolean containsEnemy = roseRules.containsEnemy(newLocation);
		Unit activeUnit = roseRules.getActiveUnit();
		if (activeUnit.canAttack() && containsEnemy) {
			roseRules.attack(newLocation);
		} else {
			roseRules.moveUnit(newLocation);
		}
	}
	
	/**
	 * @param newLocation
	 * @return
	 */
	private boolean testMoveLegal(Point location) {
		if (selected != Selection.UNIT) {
			return false;
		}
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
		Point newLocation = roseRules.getCurrentLocation();
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
		roseRules.specialAction(action);
	}
	
	/**
	 * TODO return building data with link and blalbla
	 * 
	 */
	public void addBuilding(String type) {
		roseRules.addBuilding(type);
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
	public TileInfo selectScreenItem(Point location) {
		selectedBuilding = null;
		
		TileInfo tileInfo = roseRules.getTileInfo(location);
		if (tileInfo.getSelection() == Selection.UNIT) {
			selectUnit(location);
			selected = Selection.UNIT;
		}else if (tileInfo.getSelection() == Selection.BUILDING) {
			selectBuilding(location);
			selected = Selection.BUILDING;
		} else {
			//
		}
		return tileInfo;
	}
	
	private void selectUnit(Point location) {
		Node node = back.getNode(location.x, location.y);
		roseRules.selectUnit(node.getUnits().get(0));
	}
	
	private void selectBuilding(Point location) {
		Node node = back.getNode(location.x, location.y);
		back.switchSelectedBuilding((Building) node.getItem());
	}
	
	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}
	
	public List<String> getPossibleActions() {
		if (selected == Selection.UNIT) {
			return roseRules.getPossibleUnitActions();
		} else if (selected == Selection.BUILDING){
			return roseRules.getPossibleBuildingActions(selectedBuilding);
		} else {
			throw new UnsupportedOperationException();
		}
		
		
	}

	/**
	 * 
	 */
	public void nextPlayer() {
		active = false;
		roseRules.nextPlayer();
	}

}
