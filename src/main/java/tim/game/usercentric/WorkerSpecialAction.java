/**
 * 
 */
package tim.game.usercentric;

import tim.data.back.Building;
import tim.game.Back;
import tim.game.factory.BuildingFactory;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class WorkerSpecialAction implements SpecialActionManager {

	Back back;
	UnitData unitData;
	
	/**
	 * @param unitData 
	 * 
	 */
	public WorkerSpecialAction(UnitData unitData) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		this.unitData = unitData;
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
			back.buildOnTile(unitData.getLocation().x, unitData.getLocation().y, building);
			break;
		}
		
	}

}