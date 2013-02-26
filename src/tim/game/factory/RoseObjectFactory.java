/**
 * 
 */
package tim.game.factory;

import tim.data.back.RoseObject;
import tim.data.unit.Builder;
import tim.data.unit.OilTruck;
import tim.data.unit.Truck;
import tim.game.ai.PlayerAI;

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
			roseObject = new Truck("truck");
			roseObject.setType("truck");
			roseObject.setImageName("truck");
		} else if (name.equals("player")) {
			roseObject = new PlayerAI("player");
			roseObject.setImageName("player");
		} else if ("oilTruck".equals(name)) {
			roseObject = new OilTruck("oilTruck");
			roseObject.setType("oilTruck");
			roseObject.setImageName("oilTruck");
		} else if ("builder".equals(name)) {
			roseObject = new Builder("builder");
			roseObject.setType("builder");
			roseObject.setImageName("builder");
		}
		return roseObject;
	}

}
