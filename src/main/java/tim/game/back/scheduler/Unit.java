/**
 * 
 */
package tim.game.back.scheduler;

import java.awt.Point;

/**
 * @author tim
 *
 */
public class Unit {
	
	private String name;
	private Order order;
	private Point location;

	/**
	 * 
	 */
	public Unit() {
	}
	
	public Unit(String name) {
		this.name = name;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
