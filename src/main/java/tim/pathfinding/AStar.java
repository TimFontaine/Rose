/**
 * 
 */
package tim.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tim.data.back.Node;
import tim.data.back.Path;

/**
 * @author tim
 *
 */
public class AStar {
	
	PathfindingMap map;
	List<AStarNode> closedList;
	SortedNodeList openList;
	Heuristic heuristic;

	public AStar(PathfindingMap map, Heuristic heuristic) {
		this.map = map;
		this.heuristic = heuristic;
		
	}
	
	public Path findShortestPath(int startX, int startY, int destinationX, int destinationY) { 
		this.closedList = new ArrayList<AStarNode>();
		this.openList = new SortedNodeList();
		map.setStartLocation(startX, startY);
		map.setGoalLocation(destinationX, destinationY);

		map.getStartNode().setDistanceFromStart(0);
		openList.clear();
		closedList.clear();
		openList.add(map.getStartNode());

		
		while (openList.size() != 0) {
			
			AStarNode current = openList.getFirst();
		
			 // check if our current Node location is the goal Node. If it is, we are done.
			if (current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
				return contstructPath(current);
			}
			
			openList.remove(current);
			closedList.add(current);
			
			
			for (AStarNode neighbour : current.getNeighbourList()) {
				boolean isNeighbourBetter = false;
				if (closedList.contains(neighbour)) {
					continue;
				}
				
				if (!neighbour.isObstacle()) {
					int neighbourDistanceFromStart = map.getDistanceBetween(current, neighbour) + 
							current.getDistanceFromStart();
					if (!openList.contains(neighbour)) {
						openList.add(neighbour);
						isNeighbourBetter = true;
					} else if (neighbourDistanceFromStart < current.getDistanceFromStart()) {
						isNeighbourBetter = true;
					} else {
						isNeighbourBetter = false;
					}
					
					if (isNeighbourBetter) {
						neighbour.setPreviousNode(current);
						neighbour.setDistanceFromStart(neighbourDistanceFromStart);
						neighbour.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbour.getX(), neighbour.getY(), 
								map.getGoalLocationX(), map.getGoalLocationY()));
					}
				}
				
				
			}
		}
		return null;
	}
	
	private Path contstructPath(Node node) {
		Path path = new Path();
		while (node.getPreviousNode() != null) {
			path.prependPath(node);
			node = node.getPreviousNode();
		}
		return path;
	}

	private class SortedNodeList {

		private ArrayList<AStarNode> list = new ArrayList<AStarNode>();

		public int size() {
			return list.size();
		}
		
		public void clear() {
			list.clear();
		}

		public void add(AStarNode node) {
			list.add(node);
			Collections.sort(list);
		}

		public boolean contains(AStarNode neighbour) {
			return list.contains(neighbour);
		}

		public void remove(AStarNode current) {
			list.remove(current);
			
		}

		public AStarNode getFirst() {
			return list.get(0);
		}
		
	}

	/**
	 * 
	 */
	public void resetMap() {
		map.initSearchMap();
	}
}
