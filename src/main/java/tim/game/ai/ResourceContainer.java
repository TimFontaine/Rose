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
	
	

}
