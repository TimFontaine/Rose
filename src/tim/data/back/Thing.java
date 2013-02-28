/**
 * 
 */
package tim.data.back;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import tim.game.Back;
import tim.game.Player;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 * a thing is an object on a map that belongs to a player
 */
public abstract class Thing extends Item implements Serializable {

	protected Back back;
	protected Player player;
	
	protected Map<String, Integer> requestMap;
	protected ResourcesRequest resourcesRequest;
	
	public Thing(String name) {
		super(name);
		back = Back.getInstance();
		requestMap = new HashMap<String, Integer>();
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
	
	
	
	
	
}
