/**
 * 
 */
package tim.game.ai.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tim.data.ai.ActionType;
import tim.data.back.Node;
import tim.game.Back;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class Grid {
	
	protected Goal goal;
	private Point location;

	private Node[][] nodes;
	protected List<Node> nodeList;
	private Back back;
	
	
	/**
	 * 
	 */
	public Grid() {
		back = GameApplicationFactory.getInstance().getBack();
		nodeList = new ArrayList<Node>();
	}
	
	public Goal defineGoal() {
		goal = new Goal();
		goal.setActionType(ActionType.NONE);
		goal.setPriority(0);
		return goal;
	}
	
	public Node getFreeNode() {
		for (int i = 0; i < nodes.length;i++) {
			for (int j = 0; j<nodes[0].length;j++) {
				System.out.println("free x:" + i + ":" + j);
				Node node = back.getNode(location.x +i, location.y + j);
				if (!node.containsItem()) {
					//node is free;
					return node;
				} 
			}
		}
		return null;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Node[][] getNodes() {
		return nodes;
	}

	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
		setLocation(nodes[0][0].getLocation());
		for (int i = 0; i<nodes.length; i++) {
			Node nodesArray[] = nodes[i];
			List<Node> subList = Arrays.asList(nodesArray);
			nodeList.addAll(subList);
		}
	}

}
