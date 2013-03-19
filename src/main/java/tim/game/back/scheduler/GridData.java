/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;

import tim.data.back.Node;
import tim.data.building.Building;

/**
 * @author tfontaine
 *
 */
public class GridData {

	private Building base;
	private List<Node> nodes;

	public Building getBase() {
		return base;
	}

	public void setBase(Building base) {
		this.base = base;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
