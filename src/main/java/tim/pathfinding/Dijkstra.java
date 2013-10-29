/**
 * 
 */
package tim.pathfinding;

import java.util.PriorityQueue;

import tim.com.client.shared.Node;
import tim.data.back.Path;

/**
 * @author tfontaine
 *
 */
public class Dijkstra {

	/**
	 * 
	 */
	public Dijkstra() {

	}
	
	public Path findClosestItem(int startX, int startY, PathfindingMap map, String type) {
//		Node start = map.getNode(startX, startY);
//		start.setDistanceFromStart(0);
//		PriorityQueue<Node> queue = new PriorityQueue<Node>();
//		queue.add(start);
//		
//		while (!queue.isEmpty()) {
//			Node current = queue.poll();
//			if (current.containsMapItem() && current.containsMapItemOfType(type)) {
//				return contstructPath(current);
//			}
//			for (Node node : current.getNeighbourList() ) {
//				int weight = node.getTravelWeight();
//				int distanceThroughU = current.getDistanceFromStart() + weight;
//				if (distanceThroughU < node.getDistanceFromStart()) {
//					queue.remove(node);
//					node.setDistanceFromStart(distanceThroughU);
//					node.setPreviousNode(current);
//					queue.add(node);
//				}
//					
//			}
//		}
//		System.out.println("dijkstra can not find:" + type);
		return null;
	}

	public Path contstructPath(Node node) {
		Path path = new Path();
		while (node.getPreviousNode() != null) {
			path.prependPath(node);
			node = node.getPreviousNode();
		}
		return path;
	}
	
}
