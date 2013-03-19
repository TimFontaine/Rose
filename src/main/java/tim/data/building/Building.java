/**
 * 
 */
package tim.data.building;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tim.data.back.BuildingState;
import tim.data.back.BuildingStateContext;
import tim.data.back.Thing;
import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 * a building is a thing that can not move
 *
 */
public class Building extends Thing {
	
	protected BuildingStateContext context;
	
	//the building connected too, to find resources (hq or storage)
	private Building resourceLink;
	
	private RESOURCELINK resourceLocation;
	
	private List<String> possibleActions;
	
	private enum RESOURCELINK {
		LOCAL,
		REMOTE
	}
	
	private BuildingData buildingData;
	
	
	/**
	 * 
	 */
	public Building(String type, String name) {
		super(name);
		buildingData = new BuildingData();
		buildingData.setType(type);
		resourceLocation = RESOURCELINK.LOCAL;
		context = new BuildingStateContext(buildingData);
		possibleActions = new ArrayList<String>();
		
		init();
		testResources();
	}
	
	/**
	 * Let the game work, give resources to building so they can finish
	 */
	private void testResources() {
		buildingData.getResourceContainer().addResource(Resource.IRON, 100);
		buildingData.getResourceContainer().addResource(Resource.OIL, 100);
	}

	/**
	 * 
	 */
	private void init() {
		possibleActions.add("worker");
		//remove, only use in graphics layer
		buildingData.setState(BuildingState.CONSTRUCTING);
	}
	
	public BuildingState getState() {
		return buildingData.getState();
	}

	public void setState(BuildingState state) {
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

	public Building getResourceLink() {
		return resourceLink;
	}

	public List<String> getPossibleActions() {
		return possibleActions;
	}

	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}

	/**
	 * @param itemName
	 */
	public void giveOrder(String itemName) {
		buildingData.setOrderName(itemName);
	}
	
//	public void setResourceLink(Building resourceLink) {
//		this.resourceLink = resourceLink;
//	}

}
