/**
 * 
 */
package tim.game.ai;

import tim.data.back.Building;
import tim.data.unit.Unit;
import tim.game.factory.BuildingFactory;

/**
 * @author tfontaine
 *
 */
public class BuildJob extends Job {
	
	Unit unit;
	String buildingType;

	/**
	 * 
	 */
	public BuildJob(Unit unit, String buildingType) {
		this.unit = unit;
		this.buildingType = buildingType;
	}
	
	public void doAction() {
		Building building = BuildingFactory.constructBuilding(buildingType);
		back.buildOnTile(unit.getX(), unit.getY(), building);
		/**
		 * TODO
		 * add dynamic system for resource cost of building
		 */
	}

}
