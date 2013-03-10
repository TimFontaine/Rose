/**
 * 
 */
package tim.data.unit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import tim.data.ai.ActionType;
import tim.data.ai.PlayerOrder;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.game.ai.RoadJob;
import tim.game.ai.data.Goal;
import tim.game.ai.data.Goal.GoalStatus;
import tim.game.ai.data.ResourceInfo;
import tim.game.ai.job.BuildJob;
import tim.game.ai.job.DeliverJob;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.ai.job.MultiStepJob;
import tim.game.ai.job.PickupJob;
import tim.game.ai.job.UseResourceJob;
import tim.game.back.scheduler.Order;
import tim.game.back.scheduler.Order.Status;

/**
 * @author tfontaine
 *
 */
public class Worker extends Unit {
	
	private Goal goal;
	private Queue<Job> jobList;

	/**
	 * @param name
	 */
	public Worker(String name) {
		super(name);
		jobList = new LinkedList<Job>();
		updateMaxStorage(5);
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

	public void giveOrder(Order order) {
		this.setOrder(order);
		initJobList();
		startNextJob();
		state = UnitState.ACTIVE;
	}
	
	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		
		job.doAction();
		
		if (job.isFinished()) {
			handleEndJob();
		}
		
	}
	
	/**
	 * 
	 */
	private void handleEndJob() {
		if (jobList.isEmpty()) {
			getOrder().setStatus(Status.FINISHED);
			state = UnitState.IDLE;
			return;
		}
		startNextJob();
	}
	
	private void startNextJob() {
		///pick the next job
		job = jobList.poll();
		job.start();
	}
	
	/**
	 * 
	 */
	private void initJobList() {
		if (!onLocation()) {
			Job job = new GotoJob(this, getOrder().getDestination());
			jobList.add(job);
		}
		Job job = null;
		switch (getOrder().getAction()) {
		case BUILD:
			job = new BuildJob(this, "factory");
			break;
		case RESOURCES:
			job = new PickupJob(this, resourceInfo.getResourceKeyByName("iron"), Integer.MAX_VALUE);
			break;
		default:
			break;
		}
		jobList.add(job);
	}
	
	private boolean onLocation(){
		if (this.getLocation().equals(getOrder().getDestination())) {
			return true;
		}
		return false;
	}
	

	private void testOnLocation(Point start) {
		Job job = new GotoJob(this, start);
		jobList.add(job);
	}
	
	private void placeBuilding() {
		String buildType = goal.getActionType().getInfo();
		Job job = new BuildJob(this, buildType);
		jobList.add(job);
	}
	
	private void useResources() {
		int[] resources = goal.getResources();
		for (int i =0; i<resources.length; i++) {
			System.out.println("use resource job:" + ResourceInfo.getInstance().getResourceByKey(i) + ":" + resources[i]);
			Job delivery = new UseResourceJob(this, i, resources[i]);
			jobList.add(delivery);
		}
	}
	
	private void deliverRequestResources() {
		//for all resources in the playerorder create deliver amount available
		int[] resources = goal.getResources();
		for (int i =0; i<resources.length; i++) {
			System.out.println("Delivery job:"  + ResourceInfo.getInstance().getResourceByKey(i) + ":" + resources[i]);
			Job delivery = new DeliverJob(this, i, resources[i]);
			jobList.add(delivery);
		}
	}

	/**
	 * @return the goal
	 */
	public Goal getGoal() {
		return goal;
	}

	/**
	 * @param goal the goal to set
	 */
	public void setGoal(Goal goal) {
		this.goal = goal;
	}

}
