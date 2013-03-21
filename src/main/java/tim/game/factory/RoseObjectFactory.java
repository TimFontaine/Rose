/**
 * 
 */
package tim.game.factory;

import tim.data.back.RoseObject;
import tim.data.unit.Unit;
import tim.data.unit.Worker;
import tim.game.ai.SimpleGoalAI;
import tim.game.ai.SimplePlayerAI;
import tim.game.back.scheduler.GridPlayer;
import tim.game.usercentric.Actor;
import tim.game.usercentric.WorkerActor;
import tim.game.usercentric.Infantry;

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
			roseObject = new Unit("worker", "worker");
			Actor actor = new WorkerActor((Worker)roseObject);
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
	
	public Unit getUnit(String name) {
		Unit unit = null;
		if (name.equals("worker")) {
			unit = new Unit("worker", "worker");
			Actor actor = new WorkerActor(unit);
			unit.setImageName("builder");
			unit.setStrength(10);
		} else if (name.equals("infantry")) {
			unit = new Unit("infantry", "infantry");
			Actor actor = new Infantry(unit);
			unit.setImageName("infantry");
			unit.setStrength(20);
			unit.setAttack(5);
		}
		return unit;
	}

}
