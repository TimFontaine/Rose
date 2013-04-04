/**
 * 
 */
package tim.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tim.data.back.Node;
import tim.data.back.TileInfo;
import tim.data.back.TileInfo.Selection;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.SpecialAction;

/**
 * @author tfontaine
 *
 */
public class RoseRules {
	
	Back back;
	ResourceInfo resourceInfo;
	Unit activeUnit;
	private Player activePlayer;
	private List<Player> playerList;
	Iterator<Player> playerIterator;

	/**
	 * 
	 */
	public RoseRules() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		resourceInfo = applicationFactory.getResourceInfo();
		
		playerList = new ArrayList<Player>();
	}
	
	/**
	 * 
	 */
	public void startGame() {
		//assert builder has all configuration			
//		CentricMapBuilder builder = new CentricMapBuilder();
//		builder.constructMap();
//		builder.constructPlayers();
//		map = builder.getMap();
//		playerList = builder.getPlayerList();
//		builder.constructUnits();
//		mapItems = builder.getMapItems();
		
		
		playerIterator = playerList.listIterator();
		nextPlayer();//take the first plaer from the list
		nextStep();
		
	}
	
	public void moveUnit(Point location) {
		back.moveUnit(activeUnit, location.x, location.y);
//		Unit unit = back.getActiveUnit();
//		Node node = back.getNode(unit.getLocation().x, unit.getLocation().y);
//		if (node.containsItem()) {
//			
//		}
	}
	
	public void attack(Point location) {
		back.attack(location);
		if (!containsEnemy(location)) {
			//tile is free, move
			back.moveUnit(location.x, location.y);
		}
	}
	
	public boolean containsEnemy(Point location) {
		Node target = back.getNode(location.x, location.y);
		Player owner = target.getOwner();
		if (owner != null && back.getActivePlayer() != target.getOwner()) {
			return true;
		}
		return false;
	}
	
	public TileInfo getTileInfo(Point location) {
		Node node = back.getNode(location.x, location.y);
		TileInfo tileInfo = new TileInfo(node);
		if (node.containsUnit()) {
			tileInfo.setSelection(Selection.UNIT);
			tileInfo.setUnits(node.getUnits());
		}else if (node.containsItem()) {
			tileInfo.setSelection(Selection.BUILDING);
			tileInfo.setBuilding((Building) node.getItem());
		} else {
			tileInfo.setSelection(Selection.NONE);
		}
		return tileInfo;
	}
	
	public List<String> getPossibleUnitActions() {
		String type = back.getActiveUnit().getType();
		return resourceInfo.getUnitActions(type);
	}
	
	public List<String> getPossibleBuildingActions(Building selectedBuilding) {
		return resourceInfo.getBuildingActions(selectedBuilding.getType());
	}
	
	public void specialAction(SpecialAction action) {
		back.getActiveBuilding().specialAction(action);
	}
	
	public void nextPlayer() {
		if (playerIterator.hasNext()) {
			activePlayer = playerIterator.next();
		} else {
			playerIterator = playerList.iterator();
			activePlayer = playerIterator.next();
		}
		activePlayer.initTurn();
	}
	
	public void nextStep() {
		activePlayer.doLogic();
	}
	
	public void nextTurn() {
		playerIterator = playerList.iterator();
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}

	/**
	 * @param playerList2
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * @return
	 */
	public Point getCurrentLocation() {
		return activeUnit.getLocation();
	}

	/**
	 * @param unit
	 */
	public void selectUnit(Unit unit) {
		activeUnit = unit;
	}

	/**
	 * @return
	 */
	public Unit getActiveUnit() {
		return activeUnit;
	}

}
