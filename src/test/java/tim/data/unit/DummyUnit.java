/**
 * 
 */
package tim.data.unit;

import java.util.List;

import tim.data.back.Path;
import tim.game.back.scheduler.Order;

/**
 * @author tim
 *
 */
public class DummyUnit extends Unit {

	/**
	 * @param name
	 */
	public DummyUnit(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.Unit#initJob()
	 */
	@Override
	public void initJob() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.data.unit.Unit#getUsedRoutes()
	 */
	@Override
	public List<Path> getUsedRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.Unit#giveOrder(tim.game.back.scheduler.Order)
	 */
	@Override
	public void giveOrder(Order order) {
		this.order = order;

	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

}
