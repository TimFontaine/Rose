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
			startNextJob();
			state = UnitState.ACTIVE;
		}
		if (state == UnitState.IDLE) {
			//there is no job assignment
			return;
		}
		job.doAction();
		
		if (job.isFinished()) {
			handleEndJob();
			
		}
		if (getName().equals("worker2") && jobList.isEmpty()) {
			int k = 5;
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
		
		//test resource available
		//for every resource in the playerorder create correct orders
		if (playerOrder.getResources() != null) {
			for (Map.Entry<String, Integer> entry : playerOrder.getResources().entrySet()) {
				int available = getAvailableResource(entry.getKey());
				int required = entry.getValue();
				if (available < required) {
					int transfer = required - available;
					String resourceLocation = ResourceInfo.getInstance().getLocation(entry.getKey());
					Job gotoMine = new GotoJob(this,resourceLocation);
					Job job = new PickupJob(this, entry.getKey() ,transfer);
					jobList.add(gotoMine);
					jobList.add(job);
				}
				
			}
		}
		//test on location;
		/**
		 * remove playerorder getX and put it in info
		 */
		if (playerOrder.getAction() != ActionType.ROAD) {
				Job job = new GotoJob(this, new Point(playerOrder.getX(),playerOrder.getY()));
				jobList.add(job);
		}
		
		if (playerOrder.getAction() == ActionType.BUILD) {
			Job job = new BuildJob(this, playerOrder.getTypeName());
			jobList.add(job);
		} else if (playerOrder.getAction() == ActionType.RESOURCES) {
			/**
			 * TODO
			 * refactor to flexible resources
			 */
			
			//for all resources in the playerorder create deliver amount available
			for (Map.Entry<String, Integer> entry : playerOrder.getResources().entrySet()) {
				Job job = null;
				job = new DeliverJob(this, entry.getKey(), entry.getValue());
				jobList.add(job);
			}
			
		} else if (playerOrder.getAction() == ActionType.ROAD) {
			Point from = (Point) playerOrder.getInfo().get("start");
			Point end = (Point) playerOrder.getInfo().get("end");
			System.out.println("order to build road from " + from.x + ":" + from.y + "to"
					+ end.x + ":" + end.y + "for unit " + getName());
			Job gotoStart = new GotoJob(this, from);
			MultiStepJob buildRoad = new MultiStepJob(this, end);
			Job roadJob = new RoadJob(this, null);
			buildRoad.setExtraJob(roadJob);
			
			if (getX() != from.x || getY() != from.y) {
				jobList.add(gotoStart);
			}
			jobList.add(buildRoad);
			
			
		}

	}
	
	private int getAvailableResource(String name) {
		if (resources.containsKey(name)) {
			return resources.get(name);
		}
		return 0;
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
