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
public class Worker extends Unit implements TransferResource {
	
	private PlayerOrder playerOrder;
	private Map<String, Integer> resources;
	
	private Queue<Job> jobList;

	/**
	 * @param name
	 */
	public Worker(String name) {
		super(name);
		jobList = new LinkedList<Job>();
		resources = new HashMap<String, Integer>();
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
		
		//the start function can set the job on finished 
//		if (job.isFinished()) {
//			handleEndJob();
//		}
		if (state == UnitState.IDLE) {
			//there is no job assignment
			return;
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
		if (playerOrder.getResources() != null) {
			for (Map.Entry<String, Integer> entry : playerOrder.getResources().entrySet()) {
				System.out.println("Pickup:" + entry.getKey() + ":" + entry.getValue());
				int available = getAvailableResource(entry.getKey());
				int required = entry.getValue();
				if (available < required) {
					//there are not enough resources, pick them up
					int transfer = required - available;
					String resourceLocation = ResourceInfo.getInstance().getLocation(entry.getKey());
					Job gotoMine = new GotoJob(this,resourceLocation);
					Job job = new PickupJob(this, entry.getKey() ,transfer);
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
	
	private int getAvailableResource(String name) {
		if (resources.containsKey(name)) {
			return resources.get(name);
		}
		return 0;
	}
	
	private void placeBuilding() {
		Job job = new BuildJob(this, playerOrder.getAction().getInfo());
		jobList.add(job);
	}
	
	private void useResources() {
		for (Map.Entry<String, Integer> entry : playerOrder.getResources().entrySet()) {
			System.out.println("use resource job:" + entry.getKey() + ":" + entry.getValue());
			Job delivery = new UseResourceJob(this, entry.getKey(), entry.getValue());
			jobList.add(delivery);
		}
	}
	
	private void deliverRequestResources() {
		//for all resources in the playerorder create deliver amount available
		for (Map.Entry<String, Integer> entry : playerOrder.getResources().entrySet()) {
			System.out.println("Delivery job:" + entry.getKey() + ":" + entry.getValue());
			Job job = new DeliverJob(this, entry.getKey(), entry.getValue());
			jobList.add(job);
		}
	}

	public PlayerOrder getPlayerOrder() {
		return playerOrder;
	}

	public void setPlayerOrder(PlayerOrder playerOrder) {
		this.playerOrder = playerOrder;
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#receiveResource(java.lang.String, int)
	 */
	@Override
	public void receiveResource(String name, int amount) {
		int available = getAvailableResource(name);
		int total = available+amount;
		resources.put(name, total);
		
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#giveResource(java.lang.String, int)
	 */
	@Override
	public void giveResource(String name, int amount) {
		int available = getAvailableResource(name);
		int total = available-amount;
		resources.put(name, total);
		
	}

}
