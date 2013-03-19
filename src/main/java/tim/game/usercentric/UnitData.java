/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;

/**
 * @author tfontaine
 *
 */
public class UnitData extends ActorData {
	
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
		setState(UnitState.IDLE);
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
