/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.back.Building;
import tim.data.back.BuildingState;
import tim.data.unit.Unit;
import tim.game.ai.data.Grid;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.BuildingFactory;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class BuildJob extends Job {
	
	String buildingType;
	Grid grid;
	ResourceInfo resourceInfo;
	int[] requiredResources;
	

	/**
	 * 
	 */
	public BuildJob(Unit unit, String buildingType) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		this.unit = unit;
		this.buildingType = buildingType;
		resourceInfo = applicationFactory.getResourceInfo();
		requiredResources = resourceInfo.getResourcesForThing(buildingType);
	}
	
	public void doAction() {
		Building building = BuildingFactory.constructBuilding(buildingType);
		//test on shortage of resources
		if (unit.hasResourcesAvailable(requiredResources)) {
			building.setState(BuildingState.IDLE);
		} else {
			building.setState(BuildingState.CONSTRUCTING);
		}
		back.buildOnTile(unit.getX(), unit.getY(), building);
		finished = true;
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}



}
