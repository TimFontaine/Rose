/**
 * 
 */
package tim.game.ai;

import java.util.EnumMap;
import java.util.Map;

import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class ResourceContainer {
	
	private EnumMap<Resource, MutableResource> resources;

	/**
	 * 
	 */
	public ResourceContainer() {
		resources = new EnumMap<MutableResource.Resource, MutableResource>(Resource.class);
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
	
	public int getAvailableResources(Resource resource) {
		if (resources.containsKey(resource)) {
			return resources.get(resource).getAmount();
		}
		return 0;
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
	

}
