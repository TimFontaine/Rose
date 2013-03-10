/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import tim.data.back.Node;

/**
 * @author tim
 *
 */
public class Grid {
	
	private Node[][] nodes;
	private int priority;
	private List<Unit> assignedUnits;
	
	private Point center;
	private GridState state;
	
	private GridStrategy strategy;
	public GridStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(GridStrategy strategy) {
		this.strategy = strategy;
	}

	GridScheduler scheduler;
	
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
	
	public void doAction() {
		PriorityQueue<Order> orderSet = new PriorityQueue<Order>();
		orderSet.addAll(strategy.getOrders());
		for (Unit unit : assignedUnits) {
			unit.setOrder(orderSet.poll());
		}
	}

	/**
	 * 
	 */
	public void definePriority() {
		priority = 100;
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
