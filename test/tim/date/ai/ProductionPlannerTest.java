/**
 * 
 */
package tim.date.ai;

import tim.data.ai.ProductionPlanner;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.game.Back;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tim
 *
 */
public class ProductionPlannerTest {

	Back back;
	ProductionPlanner planner;
	
	/**
	 * 
	 */
	public ProductionPlannerTest() {
		back = GameApplicationFactory.getInstance().getBack();
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		planner = new ProductionPlanner(null);
	}

	private void initMap() {
		Item mine = new Mine("mine");
		Item oilwell = new Oilwell("oilwell");
		
		mine.setX(2);
		mine.setY(2);
		mine.setType("mine");
		back.addItem(mine);
		
		oilwell.setX(6);
		oilwell.setY(8);
		oilwell.setType("oilwell");
		back.addItem(oilwell);
		
		Factory factory = new Factory("factory0");
		factory.setX(16);
		factory.setY(16);
		factory.setType("factory");
		back.addBuilding(player, factory);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ProductionPlannerTest();

	}

}
