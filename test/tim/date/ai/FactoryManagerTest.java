/**
 * 
 */
package tim.date.ai;

import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tim.data.back.Factory;
import tim.game.Back;
import tim.game.ai.FactoryManager;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tim
 *
 */
public class FactoryManagerTest {
	
	Back back;
	Factory factory;
	FactoryManager manager;

	/**
	 * 
	 */
	public FactoryManagerTest() {
		
	}
	
	/**
	 * 
	 */
	@Before
	public void init() {
		back = Back.getInstance();
		factory = new Factory("factory0");
		manager = new FactoryManager();
		manager.setFactory(factory);
	}

	@Test
	public void factoryNoResourceNeeded() {
		//get required resources (factory only builds workers)
		Map<String,Integer> resources = ResourceInfo.getInstance().getResourcesForThing("worker");
		//set required resources to factory
		factory.setResources(resources);
		Set set = manager.analyse();
		assertTrue(set.isEmpty());
	}
	
	/**
	 * //factory has no resources now, but needs resources for worker
	 */
	@Test
	public void factoryResourceNeeded() {
		Set<ResourcesRequest> set = manager.analyse();
		assertTrue(set.size() == 1);
		ResourcesRequest request;
		request = (ResourcesRequest) set.toArray()[0];
		Map<String, Integer> resources = request.getResource();
		assertEquals(2, resources.size());
		assertTrue(resources.containsKey("iron"));
		assertTrue(resources.containsKey("oil"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FactoryManagerTest();
	}

}
