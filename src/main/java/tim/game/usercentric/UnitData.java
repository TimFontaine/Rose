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
	private ComplexOrder complexOrder;
	private UnitState state;
	
	public enum UnitState {
		IDLE,
		ACTION,
		MULTI;
	}

	/**
	 * 
	 */
	public UnitData() {
		location = new Point();
		setState(UnitState.IDLE);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public ComplexOrder getComplexOrder() {
		return complexOrder;
	}

	public void setComplexOrder(ComplexOrder complexOrder) {
		this.complexOrder = complexOrder;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

}
