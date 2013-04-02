/**
 * 
 */
package tim.game.factory;

import java.awt.Point;

import tim.data.back.Node;
import tim.data.back.RoseObject;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.data.unit.Worker;
import tim.game.Back;
import tim.game.ai.ResourceContainer;
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
	
	private Back back;

	/**
	 * 
	 */
	private RoseObjectFactory() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
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
			unit.setImageName("builder");
			unit.setStrength(10);
		} else if (name.equals("infantry")) {
			unit = new Unit("infantry", "infantry");
			unit.setImageName("infantry");
			unit.setStrength(20);
			unit.setAttack(5);
		}
		return unit;
	}
	
	public Building getBuilding(String name, Unit source) {
		Building building = new Building(name, name);
		building.setLocation(source.getLocation());
		scanEnvironmentForStorage(source.getLocation(), building);
		return building;
	}
	
	private void scanEnvironmentForStorage(Point start, Building target) {
		int widthToScan = 2;
		int unitX = start.x;
		int unitY = start.y;
		
		int left = start.x - widthToScan;
		int right = start.x + widthToScan;
		int up = start.y - widthToScan;
		int down = start.y + widthToScan;
		if (left<0) left = 0;
		if (up<0) up= 0;
		if (right >= back.getBounderies().x) right = back.getBounderies().x-1;
		if (down >= back.getBounderies().y) down = back.getBounderies().y-1;
		
		for (int x = left; x <= right;x++) {
			for (int y = up; y <= down;y++) {
				Node node = back.getNode(unitX + x, unitY + y);
				if (node.containsItem() && node.getItem() instanceof Building) {
					Building link = (Building) node.getItem();
					target.setResourceLink(link.getResourceContainer());
					return;
				}
			}
		}
		//no remote resources found
		target.setResourceLink(new ResourceContainer());
	}
	
}
