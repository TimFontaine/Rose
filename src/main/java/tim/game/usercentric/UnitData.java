/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

/**
 * @author tfontaine
 *
 */
public class UnitData {
	
	private Point location;

	/**
	 * 
	 */
	public UnitData() {
		location = new Point();
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
