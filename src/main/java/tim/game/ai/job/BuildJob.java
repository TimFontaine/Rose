/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;
import java.util.EnumMap;

import tim.data.back.Building;
import tim.data.back.BuildingState;
import tim.data.back.Node;
import tim.data.unit.Unit;
import tim.game.ai.data.Grid;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.factory.BuildingFactory;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class BuildJob extends Job {
	
	ResourceInfo resourceInfo;
	
	String buildingType;
	Grid grid;

	/**
	 * 
	 */
	public BuildJob(Unit unit, String buildingType) {
		this.unit = unit;
		this.buildingType = buildingType;
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
	}
	
	public void doAction() {
		EnumMap<Resource, Integer> required = resourceInfo.getResourcesForThing(buildingType);
		
		//1. create building
		Building building = BuildingFactory.constructBuilding(buildingType);
		setupResourceLink(building);
		
		//test on shortage of resources
		if (unit.hasResourcesAvailable(required)) {
			building.setState(BuildingState.IDLE);
		} else {
			building.setState(BuildingState.CONSTRUCTING);
		}
		//2.place building on map
		back.buildOnTile(unit.getX(), unit.getY(), building);
		System.out.println("build job has build " + buildingType);
		finished = true;
	}

	/**
	 * scan the environment for buildings to link resources
	 */
	private void setupResourceLink(Building building) {
		int widthToScan = 2;
		int unitX = unit.getX(); 
		int unitY = unit.getY();
		for (int x = -widthToScan; x < widthToScan;x++) {
			for (int y = -widthToScan; y < widthToScan;y++) {
				Node node = back.getNode(unitX + x, unitY + y);
				if (node.containsItem() && node.getItem().getType().equals("hq")) {
					Building link = (Building) node.getItem();
					building.switchResourceLink(link);
					return;
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.Job#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}



}
