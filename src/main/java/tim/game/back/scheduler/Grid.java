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
import tim.data.unit.Unit;

import tim.data.back.Building;
import tim.data.back.Node;
import tim.game.back.scheduler.Order.OrderAction;

/**
 * @author tim
 *
 */
public class Grid {
	
	private Node[][] nodes;
	private int priority;
	private List<Unit> assignedUnits;
	private List<Building> buildings;
	
	private Point center;
	private GridState state;
	
	private GridScheduler scheduler;
	
	private GridStrategy strategy;
	private GridData gridData;

	public enum GridState {
		BASE;
	}
	
	/**
	 * 
	 */
	public Grid(Node[][] nodes) {
		gridData = new GridData();
		this.nodes = nodes;
		setAssignedUnits(new ArrayList<Unit>());
		scheduler = new SimpleGridScheduler();
		buildings = new ArrayList<Building>();
		
		List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i <nodes.length; i++) {
			for (int j = 0; j<nodes[0].length;j++) {
				nodeList.add(nodes[i][j]);
			}
		}
		gridData.setNodes(nodeList);
		
	}
	
	public void init() {
		strategy = new BaseGridStrategy(gridData);
	}
	
	public void addUnit(Unit unit) {
		assignedUnits.add(unit);
	}
	
	public void addBuilding(Building building) {
		buildings.add(building);
	}
	
	public void doAction() {
		strategy.doAction();
		List<Order> orderList = strategy.getOrders();
		scheduler.doAction(assignedUnits, orderList);
	
	}

	/**
	 * 
	 */
	private void createBuildOrder() {
		Order order = new Order();
		order.setDestination(new Point(1,1));
		order.setAction(OrderAction.BUILD);
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

	/**
	 * @return the scheduler
	 */
	public GridScheduler getScheduler() {
		return scheduler;
	}

	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(GridScheduler scheduler) {
		this.scheduler = scheduler;
	}

	public GridStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(GridStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void setBase(Building base) {
		gridData.setBase(base);
	}
}
