/**
 * 
 */
package tim.game;

import java.util.List;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;

/**
 * @author tim
 *
 */
public class TestAStar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map map = new Map();
		map.setupTiles(20, 20,10);
		map.getNode(0, 4).setObstacle(true);
		
		AStar star = new AStar(map, new ClosestHeuristic());
		Path path = star.findShortestPath(19,19, 0,0);
		List<Node> pathNodes  = path.getPathNodes();
		for(Node node: pathNodes) {
			System.out.println("node x:" + node.getX() + " y:" + node.getY());
		}
		
	}

}
