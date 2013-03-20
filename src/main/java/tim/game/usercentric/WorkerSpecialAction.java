/**
 * 
 */
package tim.game.usercentric;

import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.factory.BuildingFactory;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class WorkerSpecialAction implements SpecialActionManager {

	Back back;
	Unit unit;
	
	/**
	 * @param unitData 
	 * 
	 */
	public WorkerSpecialAction(Unit unit) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		this.unit = unit ;
		back = applicationFactory.getBack();
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.SpecialActionManager#doAction(tim.game.usercentric.SpecialAction)
	 */
	@Override
	public void doAction(SpecialAction action) {
		switch (action) {
		case BUILD:
			String buildingType = action.getData();
			Building building = BuildingFactory.constructBuilding(buildingType);
			back.buildOnTile(unit.getLocation().x, unit.getLocation().y, building);
			break;
		}
		
	}

}
