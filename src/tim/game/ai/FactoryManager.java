/**
 * 
 */
package tim.game.ai;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import tim.data.back.Factory;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tim
 *
 */
public class FactoryManager {
	
	private Factory factory;
	
	SortedSet<ResourcesRequest> resultRequest;
	
	public FactoryManager() {
		resultRequest = new TreeSet<ResourcesRequest>();
	}
	
	public SortedSet<ResourcesRequest> analyse() {
		String thing = defineToBuild();
		Map<String, Integer> requiredResources = defineRequiredResources(thing);
		//test resources required
		if (!requiredResources.isEmpty()) {
			resultRequest = createResourceRequest(thing, requiredResources);
		}
		return resultRequest;
		
	}

	private SortedSet<ResourcesRequest> createResourceRequest(String thing,
			Map<String, Integer> requiredResources) {
		System.out.println("created resourcerequest");
		ResourcesRequest resourcesRequest = new ResourcesRequest();
		resourcesRequest.setPriority(ResourcesRequest.MAX_PRIORITY);
		resourcesRequest.setResource(requiredResources);
		resourcesRequest.setRequestType(RequestType.RESOURCES);
		resourcesRequest.setLocation(factory.getLocation());
		resultRequest.add(resourcesRequest);
		/**
		 * TODO
		 * fix order system, this does not belong here
		 */
		factory.setResourcesRequest(resourcesRequest);
		return resultRequest;
	}

	private Map<String, Integer> defineRequiredResources(String thing) {
		Map<String,Integer> resourcesRequired = new HashMap<String, Integer>(); 
		Map<String,Integer> resources = ResourceInfo.getInstance().getResourcesForThing(thing);
		for (Map.Entry<String, Integer> resource : resources.entrySet() ) {
			int available = 0;
			if (factory.getResources().containsKey(resource.getKey())) {
				available = factory.getResources().get(resource.getKey());
			}
			int required = resource.getValue();
			int need = required - available;
			if (need <= 0) {
				need = 0;
			}else {
				resourcesRequired.put(resource.getKey(), need);
			}
		}
		return resourcesRequired;
	}

	private String defineToBuild() {
		return "worker";
	}

	/**
	 * @return the factory
	 */
	public Factory getFactory() {
		return factory;
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
}
