/**
 * 
 */
package tim.data.unit;

import java.awt.Point;
import java.util.List;

import tim.data.back.Path;
import tim.data.back.Thing;
import tim.game.Player;
import tim.game.ai.ResourceContainer;
import tim.game.ai.Task;
import tim.game.ai.job.Job;
import tim.game.back.scheduler.Order;
import tim.game.usercentric.Actor;

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
	protected Order order;
	
	private Actor actor;
	
	private ResourceContainer resourceContainer;
	
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

	/**
	 * @param location
	 */
	public void setLocation(Point location) {
		this.setX(location.x);
		this.setY(location.y);
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}

	public void setResourceContainer(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
	}

}
