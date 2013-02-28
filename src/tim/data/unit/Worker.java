/**
 * 
 */
package tim.data.unit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tim.data.ai.ActionType;
import tim.data.ai.PlayerOrder;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.game.ai.job.BuildJob;
import tim.game.ai.job.DeliverJob;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.ai.job.PickupJob;

/**
 * @author tfontaine
 *
 */
public class Worker extends Unit implements TransferResource {
	
	private int iron;
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
	}
	
	private boolean testNewOrder() {
		return playerOrder != null && jobList.isEmpty();
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
		//oil
		if (playerOrder.getOil() > this.getOil()) {
//			Path path = back.findNearestObject(locationX, locationY, "oilwell");
			Job gotoOil = new GotoJob(this, "oilwell");
			Job job = new PickupJob(this, "oil" ,playerOrder.getOil());
			jobList.add(gotoOil);
			jobList.add(job);
		}
		//iron
		if (playerOrder.getIron() > this.getIron()) {
//			Path path = back.findNearestObject(locationX, locationY, "mine");
			Job gotoMine = new GotoJob(this, "mine");
			Job job = new PickupJob(this, "iron" ,playerOrder.getIron());
			jobList.add(gotoMine);
			jobList.add(job);
		}
		
		//test on location;
		if (getX() != playerOrder.getX() || getY() != playerOrder.getY()) {
//			Path path = back.findShortestPath(locationX, locationY, playerOrder.getX(), playerOrder.getY());
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
			Job job = null;
			if (playerOrder.getIron() > 0) {
				job = new DeliverJob(this, "iron", playerOrder.getIron());
			} else if (playerOrder.getOil() > 0) {
				job = new DeliverJob(this, "oil", playerOrder.getIron());
			}
			jobList.add(job);
		}

	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
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
		if ("iron".equals(name)) {
			iron+=amount;
		} else if ("oil".equals(name)) {
			oil+=amount;
		}
		
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#giveResource(java.lang.String, int)
	 */
	@Override
	public void giveResource(String name, int amount) {
		if ("iron".equals(name)) {
			iron-=amount;
		} else if ("oil".equals(name)) {
			oil-=amount;
		}
		if (getIron() <0 || getOil() < 0) {
			System.out.println("error:" + getName() + "has negative resources");
		}
		
	}

}
