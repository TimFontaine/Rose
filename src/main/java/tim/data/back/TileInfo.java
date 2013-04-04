/**
 * 
 */
package tim.data.back;

import java.util.List;

import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * information for the graphics layer
 *
 */
public class TileInfo {
	
	ResourceInfo resourceInfo;
	
	public enum Selection {
		UNIT,
		BUILDING,
		NONE;
		
		Thing selected;
		
		public void setSelected(Thing thing) {
			this.selected = thing;
		}
		
		public Thing getSelected() {
			return selected;
		}
	}
	
	private Selection selection;
	private List<Unit> units;
	private Building building;
	
	private List<String> possibleActions;
	
	private ResourceItem resourceItem;
	
	Node node;
	//transport
	//node info

	/**
	 * @param node 
	 * 
	 */
	public TileInfo(Node node) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		this.node = node;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
		selection.setSelected(units.get(0));
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
		selection.setSelected(building);
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public List<String> getPossibleActions() {
		if (selection == Selection.UNIT) {
			List<String> actions;
			actions = resourceInfo.getUnitActions(selection.getSelected().getType());
			return actions;
		} else {
			return resourceInfo.getBuildingActions(selection.getSelected().getType());
		}
	}

	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}

}
