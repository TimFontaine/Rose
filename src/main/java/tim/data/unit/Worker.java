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

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
		
		if (testNewOrder()) {
			//test requirements for the playerorder
			setRequirementList();
			System.out.println(getName() + "has order" + goal.getActionType().toString());
			startNextJob();
			state = UnitState.ACTIVE;
		}
		
		
	
		//this should not occur
		if (state == UnitState.IDLE) {
			//there is no job assignment
			return;
		}
		
		//this is an error
		if (job == null) {
			System.out.println("job is null");
		}

		//the start function can set the job on finished
		//e.x gotoJob is on destination already
		if (job.isFinished()) {
			handleEndJob();
		}
		
		job.doAction();
		
		if (job.isFinished()) {
			handleEndJob();
			
		}
		
	}
	
	private boolean testNewOrder() {
		return state == UnitState.IDLE && goal != null && jobList.isEmpty();
	}
	
	private void handleEndJob() {
		//all done ?
		if (jobList.isEmpty()) {
			state = UnitState.IDLE;
			//update goal status
			goal.setStatus(GoalStatus.FINISHED);
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
	private void setRequirementList() {
		/*
		 * add for other stuff as task build
		 */
		
		getRequiredResources();
		Point start = goal.getDestination();
		testOnLocation(start);
		//test on location;
		switch(goal.getActionType()) {
			case BUILD:
				placeBuilding();
//				useResources();
				deliverRequestResources();
				break;
			case RESOURCES:
				deliverRequestResources();
				break;
			case ROAD:
//				Point end = (Point) playerOrder.getInfo().get("end");
//				System.out.println("order to build road from " + start.x + ":" + start.y + "to"
//						+ end.x + ":" + end.y + "for unit " + getName());
//				MultiStepJob buildRoad = new MultiStepJob(this, end);
//				Job roadJob = new RoadJob(this);
//				buildRoad.setExtraJob(roadJob);
//				
//				jobList.add(buildRoad);
//				break;
		}

	}
	
	/**
	 * 
	 */
	private void getRequiredResources() {
		//test resource available
		//for every resource in the playerorder create correct orders
		//bug? if total resources is 0, skip goto source
		int totalResources = 0;
		
		int[] orderResources = goal.getResources();
		if (orderResources != null) {
			for (int key=0; key<orderResources.length;key++) {
				ResourceInfo info = ResourceInfo.getInstance();
				System.out.println("Pickup:" + info.getResourceByKey(key) + ":" + orderResources[key]);
				int available = getAvailableResources(key);
				int required = orderResources[key];
				if (available < required) {
					//there are not enough resources, pick them up
					int transfer = required - available;
					String resourceLocation = resourceInfo.getLocation(key);
					if (resourceLocation == null) {
						int k = 0;
						System.out.println("error no resourcelocation");
					}
					Job gotoMine = new GotoJob(this,resourceLocation);
					Job job = new PickupJob(this, key ,transfer);
					jobList.add(gotoMine);
					jobList.add(job);
				}
			}
			
		}
		
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
