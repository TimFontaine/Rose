/**
 * 
 */
package tim.game.usercentric;

import java.util.List;

import tim.data.back.MapItem;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.back.scheduler.Order;

/**
 * @author tfontaine
 *
 */
public class CentricWorker extends Unit {

	/**
	 * @param name
	 */
	public CentricWorker(String name) {
		super(name);
		setType("worker");
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}

}
