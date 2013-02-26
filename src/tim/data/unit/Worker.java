/**
 * 
 */
package tim.data.unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tim.data.ai.PlayerOrder;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.game.ai.BuildJob;
import tim.game.ai.GotoJob;
import tim.game.ai.Job;
import tim.game.ai.PickupJob;

/**
 * @author tfontaine
 *
 */
public class Worker extends Unit {
	
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
		
		if (playerOrder != null && jobList.isEmpty()) {
			//test requirements for the playerorder
			setRequirementList();
			job = jobList.poll();
			state = UnitState.ACTIVE;
		}
		job.doAction();
		
		if (job.isFinished()) {
			//all done ?
			if (jobList.isEmpty()) {
				state = UnitState.IDLE;
				return;
			}
			///pick the next job
			job = jobList.poll();
			
		}
	}

	/**
	 * 
	 */
	private void setRequirementList() {
		/*
		 * add for other stuff as task build
		 */
		
		int locationX = getX();
		int locationY = getY();
		//test resource available
		//oil
		if (playerOrder.getOil() > this.getOil()) {
			Path path = back.findNearestObject(locationX, locationY, "oilwell");
			Job job = new PickupJob(this, path, "oil" ,playerOrder.getOil());
			jobList.add(job);
			Node latest = path.getLast();
			locationX = latest.getX();
			locationY = latest.getY();
		}
		//iron
		if (playerOrder.getIron() > this.getIron()) {
			Path path = back.findNearestObject(locationX, locationY, "mine");
			Job job = new PickupJob(this, path, "iron", playerOrder.getIron());
			jobList.add(job);
			Node latest = path.getLast();
			locationX = latest.getX();
			locationY = latest.getY();
		}
		
		//test on location;
		if (getX() != playerOrder.getX() || getY() != playerOrder.getY()) {
			Path path = back.findShortestPath(locationX, locationY, playerOrder.getX(), playerOrder.getY());
			Job job = new GotoJob(this, path);
			jobList.add(job);
		}
		
		job = new BuildJob(this, playerOrder.getTypeName());
		jobList.add(job);

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

}
