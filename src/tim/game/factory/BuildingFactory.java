/**
 * 
 */
package tim.game.factory;

import tim.data.back.Building;
import tim.data.back.BuildingState;
import tim.data.back.Factory;

/**
 * @author tfontaine
 *
 */
public class BuildingFactory {

	/**
	 * 
	 */
	public BuildingFactory() {
	}
	
	public static Building constructBuilding(String buildingType) {
		Building building = null;
		if ("factory".equals(buildingType)) {
			building = new Factory("factory");
			building.setImageName("factory");
			building.setType("factory");
			building.setState(BuildingState.CONSTRUCTING);
		}
		return building;
	}

}
