/**
 * 
 */
package tim.game;

import java.awt.Point;
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

	/**
	 * 
	 */
	public RoseRules() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		resourceInfo = applicationFactory.getResourceInfo();
	}
	
	public void moveUnit(Point location) {
		back.moveUnit(location.x, location.y);
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
		TileInfo tileInfo = new TileInfo();
		Node node = back.getNode(location.x, location.y);
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

}
