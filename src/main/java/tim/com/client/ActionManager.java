/**
 * 
 */
package tim.com.client;

import java.util.HashMap;
import java.util.Map;

import tim.data.back.Direction;

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

	public void initializeActions() {
		for (Direction d : Direction.values()) {
			add(new MoveAction(d));
		}
	}
	
	private void add(RoseAction action) {
		String id = action.getId();
		actions.put(id, action);
	}
	
	public RoseAction getRoseAction(String id) {
		return actions.get(id);
	}

	public Map<String, RoseAction> getActions() {
		return actions;
	}
	
}
