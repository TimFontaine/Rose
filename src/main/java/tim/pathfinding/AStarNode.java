/**
 * 
 */
package tim.pathfinding;

import java.util.List;

import tim.com.client.shared.Node;

/**
 * @author tfontaine
 *
 */
public class AStarNode extends Node implements Comparable<AStarNode> {
	
	private boolean goal;
	private boolean start;
	private boolean obstacle;
	private int distanceFromStart;
	int heuristicDistanceFromGoal;
	private transient List<AStarNode> neigborNodes;

	/**
	 * 
	 */
	public AStarNode(int x, int y)  {
		super(x,y);
		this.distanceFromStart = Integer.MAX_VALUE;
	}
	
	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	/**
	 * @return the start
	 */
	public boolean isStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean isObstacle() {
//		if (unit != null) {
//			return true;
//		}
		return obstacle;
	}

	/**
	 * @param obstacle the obstacle to set
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	
	/**
	 * @return the heuristicDistanceFromGoal
	 */
	public int getHeuristicDistanceFromGoal() {
		return heuristicDistanceFromGoal;
	}

	/**
	 * @param heuristicDistanceFromGoal the heuristicDistanceFromGoal to set
	 */
	public void setHeuristicDistanceFromGoal(int heuristicDistanceFromGoal) {
		this.heuristicDistanceFromGoal = heuristicDistanceFromGoal;
	}
	
	/**
	 * @return the distanceFromStart
	 */
	public int getDistanceFromStart() {
		return distanceFromStart;
	}

	/**
	 * @param distanceFromStart the distanceFromStart to set
	 */
	public void setDistanceFromStart(int distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}
	
	public void setNeighborList(List<AStarNode> neighbors) {
		neigborNodes = neighbors;
	}
	
	public List<AStarNode> getNeighbourList() {
		return neigborNodes;
	}
	
	public int compareTo(AStarNode other) {
		int thisTotalDistanceFromGoal = heuristicDistanceFromGoal + distanceFromStart;
		int otherTotalDistanceFromGoal = other.getHeuristicDistanceFromGoal() + other.getDistanceFromStart();
		if (thisTotalDistanceFromGoal < otherTotalDistanceFromGoal) {
			return -1;
		} else if(thisTotalDistanceFromGoal > otherTotalDistanceFromGoal) {
			return 1;
		} else {
			return 0;
		}
		
		
	}

}
