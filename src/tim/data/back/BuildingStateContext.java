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
		state = BuildingState.CONSTRUCTING;
		this.building = building;
		strategy = new BuildingConstructionStrategy(building, this);
	}
	
	public void doLogic() {
		strategy.doAction(this);
	}

	/**
	 * @param idle
	 */
	public void switchState(BuildingState newState) {
		switch (newState) {
		case IDLE:
			strategy = new BuildingWorkingStrategy(building);
			break;
		}
	}

	

}
