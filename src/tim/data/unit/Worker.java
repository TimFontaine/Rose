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
	
	private PlayerOrder playerOrder;
	
	private Queue<Job> jobList;

	/**
	 * @param name
	 */
	public Worker(String name) {
		super(name);
		jobList = new LinkedList<Job>();
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
		
		System.out.println("locic for:" + getName());
		if (testNewOrder()) {
			//test requirements for the playerorder
			setRequirementList();
			System.out.println(getName() + "has order" + playerOrder.getAction().toString());
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
		return state == UnitState.IDLE && playerOrder != null && jobList.isEmpty();
	}
	
	private void handleEndJob() {
		//all done ?
		if (jobList.isEmpty()) {
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
	private void setRequirementList() {
		/*
		 * add for other stuff as task build
		 */
		
		getRequiredResources();
		Point start = (Point) playerOrder.getInfo().get("start");
		testOnLocation(start);
		//test on location;
		switch(playerOrder.getAction()) {
			case BUILD:
				placeBuilding();
				useResources();
				break;
			case RESOURCES:
				deliverRequestResources();
				break;
			case ROAD:
				Point end = (Point) playerOrder.getInfo().get("end");
				System.out.println("order to build road from " + start.x + ":" + start.y + "to"
						+ end.x + ":" + end.y + "for unit " + getName());
				MultiStepJob buildRoad = new MultiStepJob(this, end);
				Job roadJob = new RoadJob(this);
				buildRoad.setExtraJob(roadJob);
				
				jobList.add(buildRoad);
				break;
		}
		
		if (playerOrder.getAction() == ActionType.BUILD) {
			
		} else if (playerOrder.getAction() == ActionType.RESOURCES) {			
		} else if (playerOrder.getAction() == ActionType.ROAD) {
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
		
		int[] orderResources = playerOrder.getResources();
		if (orderResources != null) {
			for (int key=0; key<orderResources.length;key++) {
				ResourceInfo info = ResourceInfo.getInstance();
				System.out.println("Pickup:" + info.getResourceByKey(key) + ":" + orderResources[key]);
				int available = resources[key];
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
		Job job = new BuildJob(this, playerOrder.getAction().getInfo());
		jobList.add(job);
	}
	
	private void useResources() {
		for (int i =0; i<playerOrder.getResources().length; i++) {
			int[] resources = playerOrder.getResources();
			System.out.println("use resource job:" + ResourceInfo.getInstance().getResourceByKey(i) + ":" + resources[i]);
			Job delivery = new UseResourceJob(this, i, resources[i]);
			jobList.add(delivery);
		}
	}
	
	private void deliverRequestResources() {
		//for all resources in the playerorder create deliver amount available
		for (int i =0; i<playerOrder.getResources().length; i++) {
			int[] resources = playerOrder.getResources();
			System.out.println("Delivery job:"  + ResourceInfo.getInstance().getResourceByKey(i) + ":" + resources[i]);
			Job delivery = new DeliverJob(this, i, resources[i]);
			jobList.add(delivery);
		}
	}

	public PlayerOrder getPlayerOrder() {
		return playerOrder;
	}

	public void setPlayerOrder(PlayerOrder playerOrder) {
		this.playerOrder = playerOrder;
	}

}
