/**
 * 
 */
package tim.com.client.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tim.com.client.RoseClient;
import tim.com.client.actions.BuildAction;
import tim.com.client.actions.BuildRoadAction;
import tim.com.client.actions.DestroyAction;
import tim.com.client.actions.DummyAction;
import tim.com.client.actions.MapControlsAction;
import tim.com.client.actions.MoveAction;
import tim.com.client.actions.RoseAction;
import tim.com.client.actions.SkipAction;
import tim.com.client.actions.TileImprovementAction;
import tim.com.client.game.InGameController;
import tim.data.back.Direction;
import tim.data.back.TileImprovementType;

/**
 * @author tfontaine
 *
 */
public class ActionManager {
	
	private Map<String, RoseAction> actions;
	
	private GUI gui;

	/**
	 * 
	 */
	public ActionManager(GUI gui) {
		actions = new HashMap<String, RoseAction>();
		this.gui = gui;
	}

	public void initializeActions(RoseClient roseClient) {
		InGameController inGameController = roseClient.getInGameController();
		add(new BuildAction(inGameController, gui));
		add(new DummyAction(gui));
		for (Direction d : Direction.values()) {
			add(new MoveAction(inGameController, d, gui));
		}
		initializeSpecialActions(roseClient, gui, inGameController);
		add(new MapControlsAction(gui));
		add(new DestroyAction(gui));
		add(new SkipAction(gui));
		add(new BuildRoadAction(gui));
		
	}
	
	public void initializeSpecialActions(RoseClient roseClient,GUI gui, InGameController inGameController) {
		for (TileImprovementType tileImprovementType : roseClient.getGame().getSpecification().getTileImprovementTypeList()) {
			add(new TileImprovementAction(inGameController, gui, tileImprovementType));
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
	
	public void updateActions() {
		for (Entry<String, RoseAction> entry : getActions().entrySet()) {
			entry.getValue().update();
		}
	}
}
