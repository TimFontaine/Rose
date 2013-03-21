/**
 * 
 */
package tim.data.back;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import tim.game.Back;
import tim.game.Player;
import tim.game.ai.ResourcesData;
import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;
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
	protected EnumMap<Resource, MutableResource> resources;
	
	
	
	public Thing(String name) {
		super(name);
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		
		resourceInfo = applicationFactory.getResourceInfo();
		resources = new EnumMap<MutableResource.Resource, MutableResource>(Resource.class);
		
	}

	public abstract void doLogic();
	
//	public ResourcesData getResourcesData() {
//		return resourcesData;
//	}
//
//	public void setResourcesData(ResourcesData resourcesData) {
//		this.resourcesData = resourcesData;
//	}
	
	public void addResource(Resource resource, int amount) {
		if (resources.containsKey(resource)) {
			resources.get(resource).update(amount);
		} else {
			MutableResource mutable = new MutableResource(resource);
			mutable.update(amount);
			resources.put(resource, mutable);
		}
	}
	
	public void retreiveResource(Resource resource, int amount) {
		resources.get(resource).update(-amount);
	}
	
	public void retreiveMultipleResources(EnumMap<Resource,Integer> requiredResources) {
		for (Map.Entry<Resource, Integer> entry : requiredResources.entrySet()) {
			retreiveResource(entry.getKey(), entry.getValue());
		}
	}
	
	public boolean hasResourcesAvailable(Map<Resource, Integer> required) {
		for (Map.Entry<Resource, Integer> entry: required.entrySet()) {
			if (!resources.containsKey(entry.getKey())) {
				return false;
			}
			int available = resources.get(entry.getKey()).getAmount();
			if (available < entry.getValue()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public boolean containsResource(Resource key) {
		if (resources.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	public boolean hasResources() {
		if (resourcesData.getTotalStorage() == 0) {
			return true;
		}
		return false;
	}
	
	public void updateMaxStorage(int max) {
//		resourcesData.setMaxStorage(max);
	}
	
	public int getAvailableResources(Resource resource) {
		if (resources.containsKey(resource)) {
			return resources.get(resource).getAmount();
		}
		return 0;
	}
	
	public int getFreeStorage() {
		return resourcesData.getFreeStorage();
	}
	
//	public ResourcesData getResourcesData() {
//		return resourcesData;
//	}
//
//	public void setResourcesData(ResourcesData resourcesData) {
//		this.resourcesData = resourcesData;
//	}
	
}
