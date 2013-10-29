/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import tim.com.client.shared.Node;

/**
 * @author tim
 *
 */
public class Path {

	private List<Node> pathNodes;
	
	public Path() {
		pathNodes = new ArrayList<Node>();
	}
	
	public void prependPath(Node node) {
		pathNodes.add(0, node); 
	}
	
	
	public List<Node> getPathNodes() {
		return pathNodes;
	}
	
	public Node first() {
		return pathNodes.get(0);
	}
	
	public Node getLast() {
		return pathNodes.get(pathNodes.size() - 1);
	}
	
	public boolean hasNext(int step) {
		if (pathNodes.size() - 1 > step) {
			return true;
		}
		return false;
		
	}
	
	public void reversePath() {
		List<Node> reverseList = new ArrayList<Node>();
		for (int i = pathNodes.size() -1; i>=0; i--) {
			reverseList.add(pathNodes.get(i));
		}
		pathNodes = reverseList;
	}
	
	
}
