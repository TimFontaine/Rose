/**
 * 
 */
package tim.data.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tim.game.ai.data.PlayerData;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;

/**
 * @author tfontaine
 *
 */
public class ProductionPlanner {
	
	PlayerData data;

	/**
	 * @param data 
	 * 
	 */
	public ProductionPlanner(PlayerData data) {
		this.data = data;
	}
	
	public Collection<ResourcesRequest> doAction() {
		List<ResourcesRequest> requests = new ArrayList<ResourcesRequest>();
		/**
		 * TODO
		 * do not build on the same location, find another spot
		 */
		if (data.capacity == 0) {
			requests.add(createBuildRequest());
		}
		requests.add(createRoadRequest());
		return requests;
		
	}
	
	/**
	 * @return
	 */
	private ResourcesRequest createRoadRequest() {
		ResourcesRequest request = new ResourcesRequest();
		request.setRequestType(RequestType.ROAD);
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("iron", 0);
		map.put("oil", 0);
		request.setResource(map);
		request.setPriority(ResourcesRequest.OPTIONAL);
		return request;
	}

	/**
	 * @return
	 */
	private ResourcesRequest createBuildRequest() {
			ResourcesRequest request = new ResourcesRequest();
			request.setRequestType(RequestType.PRODUCTION);
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("iron", 30);
			map.put("oil", 20);
			request.setResource(map);
			request.setPriority(50);
			
			return request;
	}

//	private PlayerOrder createBuildAction() {
//		PlayerOrder playerOrder = new PlayerOrder();
//		playerOrder.setTypeName("factory");
//		playerOrder.setX(14);
//		playerOrder.setY(14);
//		playerOrder.setIron(30);
//		playerOrder.setOil(20);
//		playerOrder.setProcessorType("worker");
//		playerOrder.setAction(ActionType.BUILD);
//		playerOrder.setPriority(100);
//		return playerOrder;
//	}
	

}
