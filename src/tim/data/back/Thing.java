/**
 * 
 */
package tim.data.back;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import tim.game.Back;
import tim.game.Player;
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
	
	protected Map<String, Integer> requestMap;
	protected ResourcesRequest resourcesRequest;
	
	protected int[] resources;
	
	public Thing(String name) {
		super(name);
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		requestMap = new HashMap<String, Integer>();
		
		ResourceInfo info = applicationFactory.getResourceInfo();
		resources = new int[info.NUM_RESOURCES];
		
	}

	public abstract void doLogic();
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map<String, Integer> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Integer> requestMap) {
		this.requestMap = requestMap;
	}

	public ResourcesRequest getResourcesRequest() {
		return resourcesRequest;
	}

	public void setResourcesRequest(ResourcesRequest resourcesRequest) {
		this.resourcesRequest = resourcesRequest;
	}
	
	public void addResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available + amount;
	}
	
	public void retreiveResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available - amount;
	}
	
	public int getAvailableResource(int key) {
		return resources[key]; 
	}
	
	
	
}
