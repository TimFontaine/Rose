/**
 * 
 */
package tim.game.ai;

import java.beans.DesignMode;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Node;

/**
 * @author tfontaine
 *
 */
public class Task {

	private List<Item> destination;
	private Item current;
	
	/**
	 * 
	 */
	public Task() {
		destination = new ArrayList<Item>();
	}
	
	public void addDestination(String name) {
		Item item = new Item();
		item.name = name;
		if (!destination.isEmpty()) {
			Item last = destination.get(destination.size() -1);
			last.next = item;
		}
		destination.add(item);
		item.next = destination.get(0);
		current = item;
		
	}
	
	public String getNext() {
		Item item = current.next;
		current = item;
		return current.name;
	}
	
	class Item {
		String name;
		Item next;
		
		
	}

}
