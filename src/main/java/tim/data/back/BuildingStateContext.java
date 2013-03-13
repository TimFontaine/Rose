/**
 * 
 */
package tim.data.back;

import tim.game.ai.ResourcesData;

/**
 * @author tfontaine
 *
 */
public class BuildingStateContext {
	
	BuildingState state;
	Building building;
	BuildingStrategy strategy;

	/**
	 * 
	 */
	public BuildingStateContext(Building building) {
		state = building.getState();
		this.building = building;
		strategy = new BuildingWaitStrategy();
	}
	
	public void doLogic() {
		strategy.doAction(this);
	}

	/**
	 * @param idle
	 */
	public void switchState(BuildingState newState) {
		switch (newState) {
		case CONSTRUCTING:
			strategy = new BuildingConstructionStrategy(building, this);
		case IDLE:
			strategy = new BuildingWorkingStrategy(building);
			break;
		case WAIT:
			strategy = new BuildingWaitStrategy();
		}
	}

	

}
