/**
 * 
 */
package tim.data.back;

import tim.data.building.Building;
import tim.data.building.BuildingConstructionStrategy;
import tim.data.building.BuildingData;
import tim.data.building.BuildingWorkingStrategy;
import tim.game.ai.ResourcesData;

/**
 * @author tfontaine
 *
 */
public class BuildingStateContext {
	
	BuildingState state;
	BuildingData buildingData;
	BuildingStrategy strategy;

	/**
	 * 
	 */
	public BuildingStateContext(BuildingData buildingData) {
		state = buildingData.getState();
		this.buildingData = buildingData;
		strategy = new BuildingConstructionStrategy(buildingData, this);
	}
	
	public void doLogic() {
		strategy.doAction(this);
	}

	/**
	 * @param idle
	 */
	public void switchState(BuildingState newState) {
//		switch (newState) {
//		case CONSTRUCTING:
//			strategy = new BuildingConstructionStrategy(buildingData, this);
//		case IDLE:
//			strategy = new BuildingWorkingStrategy(buildingData);
//			break;
//		case WAIT:
//			strategy = new BuildingWaitStrategy();
//		}
	}
	
	public void setState(BuildingStrategy strategy) {
		this.strategy = strategy;
	}

	

}
