/**
 * 
 */
package tim.data.back;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import tim.game.Back;
import tim.game.Player;
import tim.game.ai.ResourcesData;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 * a thing is an object on a map that belongs to a player
 */
public abstract class Thing extends Item implements Serializable {

	protected Back back;
	protected Player player;
	
	protected ResourcesRequest resourcesRequest;
	
	protected int maxStorage = 100;
	protected int totalStorage;
	
	protected ResourceInfo resourceInfo;
	
	protected ResourcesData resourcesData;
	
	public Thing(String name) {
		super(name);
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		
		resourceInfo = applicationFactory.getResourceInfo();
		resourcesData = new ResourcesData();
		
	}

	public abstract void doLogic();
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ResourcesRequest getResourcesRequest() {
		return resourcesRequest;
	}

	public void setResourcesRequest(ResourcesRequest resourcesRequest) {
		this.resourcesRequest = resourcesRequest;
	}
	
	public ResourcesData getResourcesData() {
		return resourcesData;
	}

	public void setResourcesData(ResourcesData resourcesData) {
		this.resourcesData = resourcesData;
	}
	
}
