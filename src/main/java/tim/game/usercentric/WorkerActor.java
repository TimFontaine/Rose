/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.MapItem;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.back.scheduler.Order;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.UnitData.UnitState;

/**
 * @author tfontaine
 * centric worker is a unit that should be extended and override basic functions
 */
public class WorkerActor extends BasicActor implements Actor{
	
	Back back;
	
	SpecialActionManager specialActionManager;
	private UnitData unitData;
	
	//link to the unit on the map
	
	/**
	 * @param unit
	 * @param name
	 */
	public WorkerActor(Unit unit) {
		super(unit);
		this.unit = unit;
		unit.setActor(this);
		unitData = new UnitData();
		specialActionManager = new WorkerSpecialAction(unit);
		
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void handleMultiOrder() {
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
		back.moveUnit(unit, x, y);
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

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#attack(java.awt.Point)
	 */
	@Override
	public void attack(Point point) {
		throw new UnsupportedOperationException();
	}
	
	public boolean canAttack() {
		return false;
	}

	/* (non-Javadoc)
	 * @see tim.game.usercentric.Actor#initTurn()
	 */
	@Override
	public void initTurn() {
		if (unitData.getState() == UnitState.MULTI) {
			handleMultiOrder();
		}
	}


}
