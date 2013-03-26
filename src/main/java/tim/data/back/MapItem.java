/**
 * 
 */
package tim.data.back;

import java.awt.Point;


/**
 * @author tfontaine
 *
 */
public class MapItem extends RoseObject {
	
	protected int x;
	protected int y;
	protected Point location;

	/**
	 * @param name
	 */
	public MapItem(String name) {
		super(name);
		location = new Point();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		location.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		location.y = y;
		this.y = y;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		this.x = location.x;
		this.y = location.y;
		this.location = location;
	}

}
