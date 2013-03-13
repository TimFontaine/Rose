/**
 * 
 */
package tim.game.factory;

import tim.data.back.Building;
import tim.data.back.BuildingState;
import tim.data.back.Factory;
import tim.data.back.Storage;

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
			building = new Building("factory");
//			building.setImageName("factory");
			building.setType("factory");
			building.setState(BuildingState.CONSTRUCTING);
		} else if ("storage".equals(buildingType)) {
			building = new Storage("storage");
			building.setState(BuildingState.CONSTRUCTING);
			building.setType("storage");
		} else if ("hq".equals(buildingType)) {
			building = new Storage("hq");
			building.setState(BuildingState.CONSTRUCTING);
			building.setType("hq");
		}
		return building;
	}

}
