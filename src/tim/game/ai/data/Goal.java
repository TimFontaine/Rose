/**
 * 
 */
package tim.game.ai.data;

import java.awt.Point;
import java.util.Map;

import tim.data.ai.ActionType;

/**
 * @author tfontaine
 *
 */
public class Goal {
	
	private ActionType actionType;
	private int priority;
	private String processor;
	private Map<String,Integer> resources;
	private Point destination;
	
	/**
	 * 
	 */
	public Goal() {
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public Map<String,Integer> getResources() {
		return resources;
	}

	public void setResources(Map<String,Integer> resources) {
		this.resources = resources;
	}

	/**
	 * @return the destination
	 */
	public Point getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Point destination) {
		this.destination = destination;
	}

}
