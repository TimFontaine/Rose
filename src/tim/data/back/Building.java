/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;

import tim.data.unit.TransferResource;
import tim.data.unit.Truck;
import tim.data.unit.Unit;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 * a building is a thing that can not move
 *
 */
public abstract class Building extends Thing {
	
	private BuildingState state;
	private ResourcesRequest waitingOrder;

	/**
	 * 
	 */
	public Building(String name) {
		super(name);
	}
	
	public BuildingState getState() {
		return state;
	}

	public void setState(BuildingState state) {
		this.state = state;
	}

}
