/**
 * 
 */
package tim.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Node;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.pathfinding.AStarNode;

/**
 * @author tfontaine
 *
 */
public class Map {
	
	private Node[][] nodes;
	
	

	/**
	 * 
	 */
	public Map(int sizeX, int sizeY) {
		nodes = new Node[sizeX][sizeY];
		for (int i = 0; i< sizeX; i++) {
			for (int j=0; j< sizeY; j++) {
				nodes[i][j] = new Node(i, j);;
			}
		}
	}
	
//	/**
//	 * @param sizeX2
//	 * @param sizeY2
//	 */
//	private void init(int sizeX, int sizeY) {
//		nodes = new AStarNode[sizeX][sizeY];
//		for (int i = 0; i< sizeX; i++) {
//			for (int j=0; j< sizeY; j++) {
//				nodes[i][j] = new AStarNode(i, j);;
//			}
//		}
//		
//		setupNeigbors(sizeX, sizeY);
//		
//	}
	
//	private void setupNeigbors(int width, int height) {
//		for (int x = 0; x< width; x++) {
//			for (int y=0; y< height; y++) {
//				List<Node> list = new ArrayList<Node>();
//				if (x + 1 < width ) {
//					list.add(nodes[x + 1][y]);
//				}
//				if (x != 0) {
//					list.add(nodes[x - 1][y]);
//				}
//				if (y + 1 < height ) {
//					list.add(nodes[x][y + 1]);
//				}
//				if (y != 0) {
//					list.add(nodes[x][y - 1]);
//				}
//				nodes[x][y].setNeighborList(list);
//				Collections.shuffle(list);
//			}
//		}
//	}


	/**
	 * @param unit
	 * @param location
	 */
	public void addUnit(Unit unit, int x, int y) {
		nodes[x][y].addUnit(unit);
	}

	/**
	 * @param unit
	 * @param x
	 * @param y
	 */
	public void removeUnit(Unit unit, int x, int y) {
		nodes[x][y].addUnit(unit);
	}

	/**
	 * @param item
	 * @param x
	 * @param y
	 */
	public void addItem(Item item, int x, int y) {
		nodes[x][y].setItem(item);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Node getNode(int x, int y) {
		return nodes[x][y];
	}

	/**
	 * @return
	 */
	public Point getBounderies() {
		return new Point(nodes.length, nodes[0].length);
	}

}
