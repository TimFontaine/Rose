/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;

/**
 * @author tim
 *
 */
public class Order implements Comparable<Order> {
	
	private int priority;
	private Status status;
	private Point destination;
	private String info;
	
	
	
	public enum Status {
		NEW,
		INPROGRESS,
		FINISHED;
	}
	
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
		setStatus(Status.NEW);
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

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the destination
	 */
	public Point getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Point destination) {
		this.destination = destination;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}



}
