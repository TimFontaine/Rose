/**
 * 
 */
package tim.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.game.Map;

/**
 * @author tim
 *
 */
public class AStar {
	
	Map map;
	List<Node> closedList;
	SortedNodeList openList;
	Heuristic heuristic;

	public AStar(Map map, Heuristic heuristic) {
		this.map = map;
		this.heuristic = heuristic;
		
	}
	
	public Path findShortestPath(int startX, int startY, int destinationX, int destinationY) { 
		this.closedList = new ArrayList<Node>();
		this.openList = new SortedNodeList();
		map.setStartLocation(startX, startY);
		map.setGoalLocation(destinationX, destinationY);

		map.getStartNode().setDistanceFromStart(0);
		openList.clear();
		closedList.clear();
		openList.add(map.getStartNode());

		
		while (openList.size() != 0) {
			
			Node current = openList.getFirst();
		
			 // check if our current Node location is the goal Node. If it is, we are done.
			if (current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
				return contstructPath(current);
			}
			
			openList.remove(current);
			closedList.add(current);
			
			
			for (Node neighbour : current.getNeighbourList()) {
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

		private ArrayList<Node> list = new ArrayList<Node>();

		public int size() {
			return list.size();
		}
		
		public void clear() {
			list.clear();
		}

		public void add(Node node) {
			list.add(node);
			Collections.sort(list);
		}

		public boolean contains(Node neighbour) {
			return list.contains(neighbour);
		}

		public void remove(Node current) {
			list.remove(current);
			
		}

		public Node getFirst() {
			return list.get(0);
		}
		
	}
}
