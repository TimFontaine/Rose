/**
 * 
 */
package tim.data.unit;

import java.util.ArrayList;
import java.util.List;

import tim.data.back.Path;
import tim.game.ai.JobTask;
import tim.game.ai.RoadJob;
import tim.game.ai.job.GotoJob;
import tim.pathfinding.Dijkstra;


/**
 * @author tfontaine
 *
 */
public class Builder extends Unit {
	
	JobTask jobTask;
	
	/**
	 * 
	 */
	public Builder(String name) {
		super(name);
		jobTask = new JobTask();
	}

	/* (non-Javadoc)
	 * @see tim.data.Unit#doLogic()
	 */
	@Override
	public void doLogic() {
		System.out.println("logic builder");
		if (job.isFinished()) {
			System.out.println("bulder goto finished");
			//now we can start building a road
			//2.a 
			//find the factory
			Path path = back.findNearestObject(this, "factory");
			System.out.println("road build job");
			job = new RoadJob(this, path);
		}
		job.doAction();
		
	}

	@Override
	public void initJob() {
		//1. go to a a nice location
		/**
		 * TODO
		 * improve dijkstra to find route to multiple locations with distance
		 */
		//1a. find a mine
		System.out.println("init builder");
		System.out.println("current post:" + x + ":" + y);
		Path path = back.findNearestObject(this, "mine");
//		Dijkstra dijkstra = new Dijkstra();
//		Path path = dijkstra.findClosestItem(x, y, back.getMap(), "mine");
		job = new GotoJob(this, path);
	}

	@Override
	public List<Path> getUsedRoutes() {
		//roadbuilding doesn't use routes
		return new ArrayList<Path>();
	}

}
