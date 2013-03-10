/**
 * 
 */
package tim.game.back.scheduler;

/**
 * @author tim
 *
 */
public class Order implements Comparable<Order> {
	
	private int priority;
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	private OrderAction action;
	
	public enum OrderAction {
		BUILD,
		RESOURCES,
		ATTACK;
	}

	/**
	 * 
	 */
	public Order() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Order o) {
		if (priority > o.getPriority()) {
			return -1;
		} else if (priority < o.getPriority()) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * @return the action
	 */
	public OrderAction getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(OrderAction action) {
		this.action = action;
	}



}
