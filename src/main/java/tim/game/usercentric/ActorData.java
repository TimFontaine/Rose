/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

/**
 * @author tfontaine
 *
 */
public abstract class ActorData {
	
	private Point location;
	
	public ActorData() {
		location = new Point();
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
