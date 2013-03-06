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
	protected int maxStorage = 100;
	protected int totalStorage;
	
	private ResourceInfo resourceInfo;
	
	public Thing(String name) {
		super(name);
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		requestMap = new HashMap<String, Integer>();
		
		resourceInfo = applicationFactory.getResourceInfo();
		resources = new int[resourceInfo.NUM_RESOURCES];
		
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
		updateTotalStorage(amount);
	}
	
	public void retreiveResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available - amount;
		//debug
		if (resources[key] <0 ) {
			System.out.println("error:" + this.getName() + "has negative resources for" + 
			resourceInfo.getResourceByKey(key) + ":" + resources[key]);
		}
		updateTotalStorage(-amount);
	}
	
	public void retreiveMultipleResources(int[] resourseSet) {
		for (int i = 0; i < resourseSet.length; i++ ) {
			retreiveResource(i, resourseSet[i]);
		}
	}
	
	
	public boolean hasResourcesAvailable(int[] required) {
		for (int key=0; key<required.length;key++) {
			int amount = required[key];
			if (resources[key] < amount) {
				return false;
			}
		}
		return true;
		
		
	}
	
	public int getAvailableResource(int key) {
		return resources[key]; 
	}
	
	public int getFreeStorage() {
		return maxStorage - totalStorage;
	}

	private void updateTotalStorage(int amount) {
		totalStorage += amount;
	}

	public int[] getResources() {
		return resources;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}
	
}
