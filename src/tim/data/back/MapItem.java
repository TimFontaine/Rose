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

	/**
	 * @param name
	 */
	public MapItem(String name) {
		super(name);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Point getLocation() {
		return new Point(x, y);
	}

}
