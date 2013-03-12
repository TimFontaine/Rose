/**
 * 
 */
package tim.data.back;

import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 * a building is a thing that can not move
 *
 */
public class Building extends Thing {
	
	private BuildingState state;
	private BuildingStateContext context;
	
	//the building connected too, to find resources (hq or storage)
	private Building resourceLink;
	
	
	/**
	 * 
	 */
	public Building(String name) {
		super(name);
		//context = new BuildingStateContext(this);
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

	public Building getResourceLink() {
		return resourceLink;
	}

	public void setResourceLink(Building resourceLink) {
		this.resourceLink = resourceLink;
	}

}
