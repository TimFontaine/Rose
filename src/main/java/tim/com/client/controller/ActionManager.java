/**
 * 
 */
package tim.com.client.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import tim.com.client.RoseClient;
import tim.com.client.actions.MapControlsAction;
import tim.com.client.actions.MoveAction;
import tim.com.client.actions.RoseAction;
import tim.com.client.game.InGameController;
import tim.data.back.ClientSpecification;
import tim.data.back.Direction;
import tim.namespacetest.client.GameActionProp;

/**
 * @author tfontaine
 *
 */
public class ActionManager {
	
	private Map<String, RoseAction> actions;
	
	private GUI gui;
	
	private ClientSpecification clientSpecification;
	
	InGameController inGameController;
	
	

	/**
	 * 
	 */
	public ActionManager(ClientSpecification clientSpecification, GUI gui) {
		actions = new HashMap<String, RoseAction>();
		this.clientSpecification = clientSpecification;
		this.gui = gui;
	}

	public void initializeActions(RoseClient roseClient) {
		inGameController = roseClient.getInGameController();
		//basic actions(move)
		
		
		for (GameActionProp actionProp : clientSpecification.getGameActionProps()) {
			if ("basicAction".equals(actionProp.getGameType())) {
				loadBasicAction(actionProp);
			} else {
				//custom action
				add(loadGameAction(actionProp));
			}
		}
		
		
//		add(new BuildCityAction(inGameController, gui));
//		add(new DummyAction(gui));
//		for (Direction d : Direction.values()) {
//			add(new MoveAction(inGameController, d, gui));
//		}
//		initializeSpecialActions(roseClient, gui, inGameController);
//		add(new MapControlsAction(gui));
//		add(new DestroyAction(gui));
//		add(new SkipAction(gui));
//		add(new BuildRoadAction(gui));
		
	}
	
	/**
	 * @param actionProp
	 */
	private void loadBasicAction(GameActionProp actionProp) {
		if ("move".equals(actionProp.getName())) {
			loadMoveAction(actionProp);
		}
	}

	/**
	 * @param actionProp
	 */
	private void loadMoveAction(GameActionProp actionProp) {
		for (Direction d : Direction.values()) {
			add(loadDynamicGameAction(actionProp, d.toString()));
		}
	}

	/**
	 * @param actionProp
	 * @param string
	 * @return
	 */
	private RoseAction loadDynamicGameAction(GameActionProp actionProp,
			String ... properties) {
		String actionPackage = "tim.com.client.actions.";
		ClassLoader classLoader = ActionManager.class.getClassLoader();
		try {
			Class<RoseAction> c = (Class<RoseAction>) classLoader.loadClass(actionPackage + actionProp.getActionClass());
			Constructor<RoseAction> constructor =  c.getDeclaredConstructor(GameActionProp.class, InGameController.class, gui.getClass(), properties.getClass() );
			RoseAction roseAction =  constructor.newInstance(actionProp, inGameController,  gui, properties);
			roseAction.init(actionProp);
			return roseAction;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
		
	}

	/**
	 * @param actionClass
	 */
	private RoseAction loadGameAction(GameActionProp actionProp) {
		String actionPackage = "tim.com.client.actions.";
		ClassLoader classLoader = ActionManager.class.getClassLoader();
		try {
			Class<RoseAction> c = (Class<RoseAction>) classLoader.loadClass(actionPackage + actionProp.getActionClass());
			Constructor<RoseAction> constructor =  c.getDeclaredConstructor(GameActionProp.class, InGameController.class, gui.getClass());
			return constructor.newInstance(actionProp, inGameController,  gui);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public void initializeSpecialActions(RoseClient roseClient,GUI gui, InGameController inGameController) {
//		for (TileImprovementType tileImprovementType : roseClient.getGame().getSpecification().getTileImprovementTypeList()) {
//			//add(new TileImprovementAction(inGameController, gui, tileImprovementType));
//		}
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
