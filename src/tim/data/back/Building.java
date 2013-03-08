/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;

import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 * a building is a thing that can not move
 *
 */
public class Building extends Thing {
	
	private BuildingState state;
	private BuildingStateContext context;
	private ResourcesRequest waitingOrder;
	
	
	private int[] requiredResources;

	/**
	 * 
	 */
	public Building(String name) {
		super(name);
		context = new BuildingStateContext(this);
	}
	
	public BuildingState getState() {
		return state;
	}

	public void setState(BuildingState state) {
		this.state = state;
	}
	
	@Override
	public void doLogic() {
		context.doLogic();
	}

	public void switchState(BuildingState newState) {
		if (state != newState) {
			
		}
	}

	public int[] getRequiredResources() {
		return requiredResources;
	}

	public void setRequiredResources(int[] requiredResources) {
		this.requiredResources = requiredResources;
	}

}
