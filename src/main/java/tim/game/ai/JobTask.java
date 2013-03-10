/**
 * 
 */
package tim.game.ai;

import java.util.ArrayList;
import java.util.List;

import tim.game.ai.job.Job;

/**
 * @author tfontaine
 *
 */
public class JobTask {

	private List<Item> destination;
	private Item current;
	
	/**
	 * 
	 */
	public JobTask() {
		destination = new ArrayList<Item>();
	}
	
	public void addDestination(Job job) {
		Item item = new Item();
		item.job = job;
		if (!destination.isEmpty()) {
			Item last = destination.get(destination.size() -1);
			last.next = item;
		}
		destination.add(item);
		item.next = destination.get(0);
		current = item;
		
	}
	
	public List<Job> getList() {
		List<Job> list = new ArrayList<Job>();
		for (Item item : destination) {
			list.add(item.job);
		}
		return list;
	}
	
	public Job getNext() {
		Item item = current.next;
		current = item;
		return current.job;
	}
	
	class Item {
		Job job;
		Item next;
		
		
	}

}
