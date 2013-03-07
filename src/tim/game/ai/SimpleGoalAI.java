/**
 * 
 */
package tim.game.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import tim.data.ai.ActionType;
import tim.data.ai.PlayerOrder;
import tim.data.back.Building;
import tim.data.back.Factory;
import tim.data.back.Node;
import tim.data.unit.Unit;
import tim.data.unit.UnitState;
import tim.data.unit.Worker;
import tim.game.Back;
import tim.game.ai.data.Goal;
import tim.game.ai.data.Goal.GoalStatus;
import tim.game.ai.data.Grid;
import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class SimpleGoalAI extends BasicPlayer {
	
	private List<Grid> grids;
	private List<Goal> goalList;

	/**
	 * 
	 */
	public SimpleGoalAI() {
		setUnits(new ArrayList<Unit>());
		grids = new ArrayList<Grid>();
		goalList = new ArrayList<Goal>();
		setupGrids();
	}
	
	/**
	 * 
	 */
	private void setupGrids() {
		//define a grid for the base;
		Grid grid = new Base();
		//assign nodes to a grid;
		Node[][] nodes = new Node[3][3];
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		//this is the upper left of the grid
		Point gridPoint = new Point(13,13);
		Back back = applicationFactory.getBack();
		//fill the grid nodes
		for (int i = 0; i <nodes.length; i++) {
			for (int j = 0; j< nodes[0].length; j++) {
				nodes[i][j] =  back.getNode(gridPoint.x + i, gridPoint.y +j);
			}
		}
		grid.setNodes(nodes);
		grids.add(grid);
	}
	
	public void assignUnits() {
		goalList.clear();
		for (Grid grid: grids) {
			defineGoalsForGrid(grid);
		}
		assignUnitToGoal();
	}
	
	public void doLogic() {
		assignUnits();
		//do units logic
		for (Building building : buildings) {
			if (building instanceof Factory) {
				Factory factory = (Factory) building;
//				factory.setUnitToProduce(new Worker("worker1"));
			}
			building.doLogic();
		}
		for (Unit unit : units) {
			unit.doLogic();
		}
	}
	
	public void defineGoalsForGrid(Grid grid) {
		//only base is interesting
		List<Goal> goals = grid.defineGoal();
		goalList.addAll(goals);
	}
	
	public void assignUnitToGoal() {
		SortedMap<Integer,Goal> map= new TreeMap<Integer,Goal>(new MyComparator());
		for (Unit unit : units) {
			if (unit.getState() == UnitState.IDLE) {
				for (Goal goal: goalList) {//undefined order
					//compute the match between a unit and a goal
					int match = computeMatchToGoal(goal, unit);
					map.put(new Integer(match),goal);
					//add the unit to the specified goal 
				}
				
				Integer key = map.firstKey();
				Goal goal = map.get(key);
				if (goal.getActionType() == ActionType.BUILD && goal.getActionType().getInfo().equals("storage")) {
					System.out.println(unit.getName() + "has order to build storage on" + goal.getDestination());
				}
				addUnitToGoal(unit, goal);
				goalList.remove(goal);
				map.clear();
			}
			
			/**
			 * TODO do something with units without task
			 */
			if (goalList.isEmpty()) {
				return;
			}
		}
	}

	/**
	 * @param unit
	 * @param grid
	 */
	private void addUnitToGoal(Unit unit, Goal goal) {
		PlayerOrder order = new PlayerOrder();
		int[] resources = goal.getResources();
		order.addInfo("start", goal.getDestination());
		order.setResources(resources);
		order.setAction(goal.getActionType());
		((Worker)unit).setPlayerOrder(order);
		
		//update goal status
		goal.setStatus(GoalStatus.INPROGRESS);
		goal.getAssignedUnits().add(unit);
		System.out.println(unit.getName() + "is assigned to" + goal.getActionType() + " " + goal.getActionType().getInfo());
	}

	/**
	 * @param unit
	 */
	private int computeMatchToGoal(Goal goal, Unit unit) {
		int distanceFromGoal = distanceToGrid(goal, unit);
		int match = distanceFromGoal * goal.getPriority();
		return match;
	}
	
	private int distanceToGrid(Goal goal, Unit unit) {
		Point source = unit.getLocation();
		Point target = goal.getDestination();
		int distance = (int)source.distance(target);
		return distance;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	static class MyComparator implements Comparator<Integer> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1.intValue() < o2) {
				return 1;
			} else if (o2 < o1) {
				return -1;
			}
			return 0;
		}
		
	}

}
