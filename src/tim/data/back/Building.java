/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;

import tim.data.unit.TransferResource;
import tim.data.unit.Truck;
import tim.data.unit.Unit;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public abstract class Building extends Thing implements TransferResource {
	
	private BuildingState state;

	/**
	 * 
	 */
	public Building(String name) {
		super(name);
	}
	
	public abstract Requirement getRequirements();

	public BuildingState getState() {
		return state;
	}

	public void setState(BuildingState state) {
		this.state = state;
	}

}
