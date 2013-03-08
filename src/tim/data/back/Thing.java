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
	
//	public ResourcesData getResourcesData() {
//		return resourcesData;
//	}
//
//	public void setResourcesData(ResourcesData resourcesData) {
//		this.resourcesData = resourcesData;
//	}
	
	public int[] getAvailableResources() {
		return resourcesData.getResources();
	}
	
	public void addResource(int key, int amount) {
		resourcesData.updateResource(key, amount);
	}
	
	public void retreiveResource(int key, int amount) {
		resourcesData.updateResource(key, -amount);
	}
	
	public void retreiveMultipleResources(int[] resourseSet) {
		for (int i = 0; i < resourseSet.length; i++ ) {
			resourcesData.updateResource(i, -resourseSet[i]);
		}
	}
	
	public boolean hasResourcesAvailable(int[] required) {
		for (int key=0; key<required.length;key++) {
			int amount = required[key];
			if (resourcesData.getAvailableResource(key) < amount) {
				return false;
			}
		}
		return true;	
	}
	
	public boolean hasResources() {
		if (resourcesData.getTotalStorage() == 0) {
			return true;
		}
		return false;
	}
	
	public int getAvailableResources(int key) {
		return resourcesData.getAvailableResource(key);
	}
	
	public int getFreeStorage() {
		return resourcesData.getFreeStorage();
	}

	public ResourcesData getResourcesData() {
		return resourcesData;
	}

	public void setResourcesData(ResourcesData resourcesData) {
		this.resourcesData = resourcesData;
	}
	
}
