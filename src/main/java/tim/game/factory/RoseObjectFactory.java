/**
 * 
 */
package tim.game.factory;

import tim.data.back.RoseObject;
import tim.data.unit.Worker;
import tim.game.ai.SimpleGoalAI;
import tim.game.ai.SimplePlayerAI;
import tim.game.back.scheduler.GridPlayer;
import tim.game.usercentric.CentricWorker;

/**
 * @author tfontaine
 *
 */
public class RoseObjectFactory {
	
	private static RoseObjectFactory INSTANCE;

	/**
	 * 
	 */
	private RoseObjectFactory() {
	}
	
	public static RoseObjectFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RoseObjectFactory();
		}
		return INSTANCE;
	}
	
	public RoseObject getRoseObject(String name) {
		RoseObject roseObject = null;
		if (name.equals("truck")) {
			//roseObject = new Truck("truck");
			roseObject.setType("truck");
			roseObject.setImageName("truck");
		} else if ("simplePlayer".equals(name)) {
			roseObject = new SimplePlayerAI("simplePlayer");
			roseObject.setImageName("player");
		} else if ("worker".equals(name)) {
			roseObject = new CentricWorker("worker");
			//roseObject = new Worker("worker");
			roseObject.setType("worker");
			roseObject.setImageName("builder");
		} else if ("simpleGoalPlayer".equals(name)) {
			roseObject = new SimpleGoalAI();
		} else if ("GridPlayer".equals(name)) {
			roseObject = new GridPlayer();
		}
		return roseObject;
	}

}
