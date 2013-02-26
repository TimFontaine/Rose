/**
 * 
 */
package tim.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.pathfinding.RandomTiles;

/**
 * @author tim
 *
 */
public class Map implements Serializable {
	
	private int sizeX;
	private int sizeY;
	private int startLocationX;
	private int startLocationY;
	private int goalLocationX;
	private int goalLocationY;
	
	private Node[][] nodes;
	
	private List<MapItem> mapItemsList;
	
	public Map() {
		mapItemsList = new ArrayList<MapItem>();
	}
	
	public void setGoalLocation(int x, int y) {
		nodes[goalLocationX][goalLocationY].setGoal(false);
		nodes[x][y].setGoal(true);
		goalLocationX = x;
		goalLocationY = y;
	}
	
	public void setStartLocation(int x, int y) {
		nodes[startLocationX][startLocationY].setStart(false);
		nodes[x][y].setGoal(true);
		startLocationX = x;
		startLocationY = y;
	}
	
	public int getDistanceBetween(Node node1, Node node2) {
//		return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
		int k =  1 * node2.getTravelWeight();
		return k;
		
	}
	
	public void resetNodes() {
		for (int i=0; i<nodes.length; i++) {
			for (int j = 0; j< nodes[0].length; j++) {
				Node node = nodes[i][j];
				node.setHeuristicDistanceFromGoal(0);
				node.setPreviousNode(null);
				node.setDistanceFromStart(Integer.MAX_VALUE);
				node.setGoal(false);
				node.setStart(false);
			}
		}
	}
	
	public Node getStartNode() {
		return nodes[startLocationX][startLocationY];
	}
	
	public int getGoalLocationX() {
		return goalLocationX;
	}
	
	public int getGoalLocationY() {
		return goalLocationY;
	}
	
	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	
	public Node getNode(int x, int y) {
		return nodes[x][y];
	}

	public void setupTiles(int x, int y, int defaultSpeed) {
		nodes = new Node[x][y];
		for (int i = 0; i< x; i++) {
			for (int j=0; j< y; j++) {
				nodes[i][j] = new Node(i, j);;
				nodes[i][j].setTravelWeight(defaultSpeed);
			}
		}
		
		setupNeigbors(x, y);
		
	}

	private void setupNeigbors(int width, int height) {
		for (int x = 0; x< width; x++) {
			for (int y=0; y< height; y++) {
				List<Node> list = new ArrayList<Node>();
				if (x + 1 < width ) {
					list.add(nodes[x + 1][y]);
				}
				if (x != 0) {
					list.add(nodes[x - 1][y]);
				}
				if (y + 1 < height ) {
					list.add(nodes[x][y + 1]);
				}
				if (y != 0) {
					list.add(nodes[x][y - 1]);
				}
				nodes[x][y].setNeighborList(list);
				RandomTiles.shuffleList(list);
			}
		}
	}
	
	public void shuffleNeighbours() {
		for (int x = 0; x< nodes.length; x++) {
			for (int y=0; y< nodes[0].length; y++) {
				Node node = nodes[x][y];
				List<Node> neigbours = node.getNeighbourList();
				Collections.shuffle(neigbours);
			}
		}
	}

	public List<MapItem> getMapItems() {
		return mapItemsList;
	}

	public List<MapItem> getMapItemsList() {
		return mapItemsList;
	}

	public void setMapItemsList(List<MapItem> mapItemsList) {
		this.mapItemsList = mapItemsList;
	}

	
}
