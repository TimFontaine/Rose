/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.back.scheduler.Order;

/**
 * @author tim
 *
 */
public class Infantry extends Unit {
	
	/**
	 * @param unit
	 */
	public Infantry(String type, String name) {
		super();
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#move(int, int)
	 */
	@Override
	public void move(int x, int y) {
		back.moveUnit(unit, x, y);
	}
	
	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#attack(java.awt.Point)
	 */
	@Override
	public void attack(Point location) {
		//fight in actor layer or back ?
		back.attack(unit,location);
		if (!back.containsEnemy(location)) {
			move(location.x, location.y);
		}
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#specialAction(tim.game.usercentric.SpecialAction)
	 */
	@Override
	public void specialAction(SpecialAction action) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#handleMultiOrder()
	 */
	@Override
	public void handleMultiOrder() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#setComplexOrder(tim.game.usercentric.ComplexOrder)
	 */
	@Override
	public void setComplexOrder(ComplexOrder order) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#initTurn()
	 */
	@Override
	public void initTurn() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#canAttack()
	 */
	@Override
	public boolean canAttack() {
		return true;
	}

}
