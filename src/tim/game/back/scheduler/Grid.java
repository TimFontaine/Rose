/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Node;
import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public class Grid {
	
	private Node[][] nodes;
	private GridState state;
	private int priority;
	private List<Unit> assignedUnits;
	
	private Point center;

	public enum GridState {
		BASE;
		
	}
	/**
	 * 
	 */
	public Grid() {
		setAssignedUnits(new ArrayList<Unit>());
	}
	
	public void addUnit(Unit unit) {
		assignedUnits.add(unit);
	}

	/**
	 * @return the nodes
	 */
	public Node[][] getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the state
	 */
	public GridState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(GridState state) {
		this.state = state;
	}

	/**
	 * 
	 */
	public void definePriority() {
		priority = 100;
	}

	/**
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * @param center the center to set
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the assignedUnits
	 */
	public List<Unit> getAssignedUnits() {
		return assignedUnits;
	}

	/**
	 * @param assignedUnits the assignedUnits to set
	 */
	public void setAssignedUnits(List<Unit> assignedUnits) {
		this.assignedUnits = assignedUnits;
	}

}
