/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.List;

import tim.data.back.MapItem;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.back.scheduler.Order;
import tim.game.usercentric.UnitData.UnitState;

/**
 * @author tfontaine
 * centric worker is a unit that should be extended and override basic functions
 */
public class CentricWorker extends Unit {
	
	SpecialActionManager specialActionManager;
	private UnitData unitData;

	/**
	 * @param name
	 */
	public CentricWorker(String name) {
		super(name);
		setType("worker");
		unitData = new UnitData();
		specialActionManager = new WorkerSpecialAction(unitData);
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
		if (unitData.getState() == UnitState.MULTI) {
			Job job = unitData.getComplexOrder().getJob();
			switch (job.getType()) {
			case PATH:
				GotoJob go = (GotoJob) job;
				Point location = go.getNext();
				move(location.x, location.y);
				if (job.isFinished()) {
					unitData.setState(UnitState.IDLE);
				}
			}
		}
	}
	
	public void move(int x, int y) {
		back.moveUnit(this, x, y);
		unitData.getLocation().x = x;
		unitData.getLocation().y = y;
	}
	
	public void specialAction(SpecialAction action){
		specialActionManager.doAction(action);
	}
	
	public void setComplexOrder(ComplexOrder order) {
		unitData.setComplexOrder(order);
		unitData.setState(UnitState.MULTI);
	}

	public UnitData getUnitData() {
		return unitData;
	}

	public void setUnitData(UnitData unitData) {
		this.unitData = unitData;
	}
	
	

}
