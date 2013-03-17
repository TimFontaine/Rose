/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 * a building is a thing that can not move
 *
 */
public class Building extends Thing {
	
	private BuildingState state;
	protected BuildingStateContext context;
	
	//the building connected too, to find resources (hq or storage)
	private Building resourceLink;
	
	private RESOURCELINK resourceLocation;
	
	private List<String> possibleActions;
	
	private enum RESOURCELINK {
		LOCAL,
		REMOTE
	}
	
	
	/**
	 * 
	 */
	public Building(String name) {
		super(name);
		resourceLocation = RESOURCELINK.LOCAL;
		context = new BuildingStateContext(this);
		possibleActions = new ArrayList<String>();
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		possibleActions.add("worker");
	}
	
	public BuildingState getState() {
		return state;
	}

	public void setState(BuildingState state) {
		this.state = state;
		context.switchState(state);
	}
	
	@Override
	public void doLogic() {
		context.doLogic();
	}
	
	public void switchResourceLink(Building resourceLink) {
		//set new resourcelink
		//move all local resources to resourcelink
		for (Map.Entry<Resource, MutableResource> entry : resources.entrySet()) {
			resourceLink.addResource(entry.getKey(), entry.getValue().getAmount());
		}
		this.resources.clear();
		//assign remote link resources to local;
		resources = resourceLink.getResources();
		resourceLocation = RESOURCELINK.REMOTE;
	}

	public void switchState(BuildingState newState) {
		if (state != newState) {
			
		}
	}

	public Building getResourceLink() {
		return resourceLink;
	}

	public List<String> getPossibleActions() {
		return possibleActions;
	}

	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}

//	public void setResourceLink(Building resourceLink) {
//		this.resourceLink = resourceLink;
//	}

}
