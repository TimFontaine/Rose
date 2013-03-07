/**
 * 
 */
package tim.game.ai.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.ai.ActionType;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class Goal implements Comparable<Goal> {
	
	private ActionType actionType;
	private int priority;
	private String processor;
	private int[] resources;
	private Point destination;

	private GoalStatus status;
	private List<Unit> assignedUnits;
	
	
	public enum GoalStatus {
		WAITING,
		INPROGRESS,
		FINISHED;
	}
	
	/**
	 * 
	 */
	public Goal() {
		status = GoalStatus.WAITING;
		setAssignedUnits(new ArrayList<Unit>());
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

	public int[] getResources() {
		return resources;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}

	/* (non-Javadoc)
	 * highest priority first
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Goal other) {
		int otherPriority = other.getPriority();
		if (priority > otherPriority) {
			return -1;
		} else if (priority < otherPriority) {
			return 1;
		}
		return 0;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}

	public List<Unit> getAssignedUnits() {
		return assignedUnits;
	}

	public void setAssignedUnits(List<Unit> assignedUnits) {
		this.assignedUnits = assignedUnits;
	}

}
