/**
 * 
 */
package tim.com.client.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tim.namespacetest.types.Resource;

/**
 * @author tim
 *
 */
public class Cargo {
	
	private int maxStorage;
	private Map<String, Resource> resources;
	
	public Cargo() {
		resources = new HashMap<String, Resource>();
	}
	
	public boolean hasResource(String resourceName) {
		return resources.containsKey(resourceName);
	}
	
	public boolean hasResource(Resource resource) {
		 Resource local = resources.get(resource.getResourceType());
		 if (local == null) {
			 return false;
		 }
		 if (local.getAmount() < resource.getAmount()) {
			 return false;
		 }
		 return true;
	}
	
	public boolean addResource(String resourceName, int amount) {
		//test free space before add
		if (!isSpaceAvailable(amount)) {
			return false;
		}
		
		Resource resource = resources.get(resourceName);
		if (resource == null) {
			resource = new Resource();
			resource.setAmount(amount);
			resource.setResourceType(resourceName);
		} else {
			int newAmount = amount + resource.getAmount();
			resource.setAmount(newAmount);
		}
		resources.put(resource.getResourceType(), resource);
		return true;
	}
	
	public boolean useResource(Resource resource) {
		Resource localResource = resources.get(resource.getResourceType());
		if (localResource == null) {
			return false;
		}
		int available = localResource.getAmount() - resource.getAmount();
		if (available < 0) {
			return false;
		}
		localResource.setAmount(available);
		return true;
	}

	public boolean isSpaceAvailable(int amount) {
		if (maxStorage > amount + getUsedSpace()) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return
	 */
	private int getUsedSpace() {
		int total = 0;
		for (Entry<String, Resource> resourceEntry : resources.entrySet()) {
			total = total + resourceEntry.getValue().getAmount();
		}
		return total;
	}

	/**
	 * @return the space
	 */
	public int getMaxStorage() {
		return maxStorage;
	}

	/**
	 * @param space the space to set
	 */
	public void setMaxStorage(int maxStorage) {
		this.maxStorage = maxStorage;
	}
}
