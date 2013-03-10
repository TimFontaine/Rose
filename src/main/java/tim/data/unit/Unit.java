/**
 * 
 */
package tim.data.unit;

import java.util.List;

import tim.data.back.Path;
import tim.data.back.Thing;
import tim.game.ai.Task;
import tim.game.ai.job.Job;

/**
 * @author tfontaine
 *
 */
public abstract class Unit extends Thing {
	
	public static final int RESOURCE_TRANSFER = 20;
	public static final int MAX_MOVES = 200;
	public static final int MIN_MOVE_COST = 1;
	protected UnitState defaultState = UnitState.IDLE;
	public static int OIL_USAGE = 1;
	
	Job job;
	Task task;
	
	protected int oil;
	private int moves;
	
	protected UnitState state;
	
	protected UnitOrder order;
	
	
	/**
	 * 
	 */
	public Unit(String name) {
		super(name);
		//setup job
		task = new Task();
		task.addDestination("mine");
		task.addDestination("factory");
		
		state= UnitState.IDLE;
	}
	
	public abstract void initJob();
	
	public abstract List<Path> getUsedRoutes();
	
	public void useOil() {
		oil-=OIL_USAGE;
	}
	

	public int getOil() {
		return oil;
	}

	public void setOil(int oil) {
//		if (this.oil <=0 && oil > 0) {
//			state = UnitState.ACTIVE;
//		}
		this.oil = oil;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public UnitOrder getOrder() {
		return order;
	}

	public void setOrder(UnitOrder order) {
		this.order = order;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public UnitState getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(UnitState defaultState) {
		this.defaultState = defaultState;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}


	

	
}
