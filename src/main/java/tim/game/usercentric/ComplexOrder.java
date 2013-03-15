/**
 * 
 */
package tim.game.usercentric;

import java.util.Deque;
import java.util.LinkedList;

import tim.game.ai.job.Job;

/**
 * @author tfontaine
 *
 */
public class ComplexOrder {
	
	Deque<Job> orders;
	Job current;
	
	/**
	 * 
	 */
	public ComplexOrder() {
		orders = new LinkedList<Job>();
	}
	
	public void addJob(Job job) {
		orders.add(job);
	}
	
	public Job getJob() {
		if (current == null || current.isFinished()) {
			current = orders.poll();
		}
		return current;
	}
	
	public boolean isFinished() {
		return current.isFinished() && orders.isEmpty();
	}
}
