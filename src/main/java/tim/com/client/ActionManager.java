/**
 * 
 */
package tim.com.client;

import java.util.HashMap;
import java.util.Map;

import tim.data.back.Direction;
import tim.data.back.TileImprovementType;
import tim.rose.buttons.actions.BuildAction;
import tim.rose.buttons.actions.TileImprovementAction;

/**
 * @author tfontaine
 *
 */
public class ActionManager {
	
	private Map<String, RoseAction> actions;
	

	/**
	 * 
	 */
	public ActionManager() {
		actions = new HashMap<String, RoseAction>();
	}

	public void initializeActions(RoseClient roseClient) {
		InGameController inGameController = roseClient.getInGameController();
		add(new BuildAction(inGameController));
		for (Direction d : Direction.values()) {
			add(new MoveAction(inGameController, d));
		}
		initializeSpecialActions(roseClient, inGameController);
		
	}
	
	private void initializeSpecialActions(RoseClient roseClient, InGameController inGameController) {
		for (TileImprovementType tileImprovementType : roseClient.getGame().getSpecification().getTileImprovementTypeList()) {
			add(new TileImprovementAction(inGameController, tileImprovementType));
		}
	}
	
	private void add(RoseAction action) {
		String id = action.getId();
		actions.put(id, action);
		System.out.println("added action with id:" + id);
	}
	
	public RoseAction getRoseAction(String id) {
		return actions.get(id);
	}

	public Map<String, RoseAction> getActions() {
		return actions;
	}
	
}
