/**
 * 
 */
package tim.data.unit;

import java.awt.Point;

import tim.data.back.Thing;
import tim.game.ai.ResourceContainer;
import tim.game.ai.Task;
import tim.game.ai.job.Job;
import tim.game.back.scheduler.Order;
import tim.game.usercentric.Actor;
import tim.game.usercentric.ComplexOrder;

/**
 * @author tfontaine
 *
 */
public class Unit extends Thing {
	
	public static final int RESOURCE_TRANSFER = 20;
	public static final int MAX_MOVES = 200;
	public static final int MIN_MOVE_COST = 1;
	protected UnitState defaultState = UnitState.IDLE;
	
	Job job;
	Task task;
	
	protected int oil;
	private int moves;
	
	protected UnitState state;
	
//	protected UnitOrder order;
	protected ComplexOrder complexOrder;
	
	
	private ResourceContainer resourceContainer;
	
	private MoveHandler moveHandler;
	
	/**
	 * 
	 */
	public Unit(String type, String name) {
		super(name);
		this.setType(type);
		//setup job
		task = new Task();
		task.addDestination("mine");
		task.addDestination("factory");
		resourceContainer = new ResourceContainer();
		state= UnitState.IDLE;
		
		setImageName("builder");
	}
	
	@Deprecated
	public void initJob() {
		
	}
	
	@Deprecated
	public void giveOrder(Order order) {
		
	}
	
	public boolean canAttack() {
		if (attack > 0) {
			return true;
		}
		return false;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

		/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}

	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}

	public void setResourceContainer(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
	}

	/**
	 * @param order
	 */
	public void setComplexOrder(ComplexOrder order) {
		this.complexOrder = order;
	}

}
