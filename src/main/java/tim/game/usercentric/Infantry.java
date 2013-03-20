/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.back.scheduler.Order;

/**
 * @author tim
 *
 */
public class Infantry extends Unit implements Actor {
	
	private UnitData unitData;

	/**
	 * @param name
	 */
	public Infantry(String name) {
		super("infantry", name);
		unitData = new UnitData();
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.Unit#initJob()
	 */
	@Override
	public void initJob() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see tim.data.unit.Unit#giveOrder(tim.game.back.scheduler.Order)
	 */
	@Override
	public void giveOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#move(int, int)
	 */
	@Override
	public void move(int x, int y) {
		if (back.getNode(x, y).containsMapItem()) {
			attack(new Point(x, y));
		}
		back.moveUnit(this, x, y);
		setLocation(new Point(x,y));
	}
	
	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#attack(java.awt.Point)
	 */
	@Override
	public void attack(Point point) {
		//fight in actor layer or back ?
		back.clearNode(point);
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#specialAction(tim.game.usercentric.SpecialAction)
	 */
	@Override
	public void specialAction(SpecialAction action) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#getData()
	 */
	@Override
	public ActorData getData() {
		return unitData;
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

}
