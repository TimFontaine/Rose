/**
 * 
 */
package tim.game.ai;

import java.util.EnumMap;

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
	
	

}
