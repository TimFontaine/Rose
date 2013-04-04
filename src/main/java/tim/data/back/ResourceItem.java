/**
 * 
 */
package tim.data.back;

import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class ResourceItem extends Item {
	
	private Resource resource;
	private int storage;

	/**
	 * @param name
	 */
	public ResourceItem(String name, Resource resource) {
		super(name);
		this.setType(name);
		this.resource = resource;
	}
	
	public void update(int amount) {
		storage+=amount; 
	}
	
	public int getStorage() {
		return storage;
	}

	public Resource getResource() {
		return resource;
	}
}
