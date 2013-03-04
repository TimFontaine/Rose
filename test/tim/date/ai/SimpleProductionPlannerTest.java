/**
 * 
 */
package tim.date.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.management.modelmbean.RequiredModelMBean;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import tim.data.ai.ActionType;
import tim.data.ai.ProductionPlanner;
import tim.data.ai.SimpleProductionPlanner;
import tim.data.back.Building;
import tim.data.back.Factory;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.MockApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class SimpleProductionPlannerTest {
	
	SimpleProductionPlanner planner;

	/**
	 * 
	 */
	public SimpleProductionPlannerTest() {
	}
	
	@Before
	public void setup() {
		List<Building> buildings = new ArrayList<Building>();
		Factory factory = new Factory("factory0");
		buildings.add(factory);
		
		planner = new SimpleProductionPlanner();
		planner.setBuildings(buildings);
	}
	
	@Test
	public void createPlanning() {
		planner.createPlanning();
		SortedSet<ResourcesRequest> requests = planner.getRequests();
		
		assertEquals(2, requests.size());
		//test that the one with the highest priority is the first
		ResourcesRequest request = requests.first();
		assertEquals(ActionType.BUILD, request.getRequestType());
		assertEquals(100, request.getPriority());
	}

}
