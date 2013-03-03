/**
 * 
 */
package tim.data.unit;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import tim.data.ai.ActionType;
import tim.data.ai.PlayerOrder;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.game.Back;
import tim.game.ai.DummyPlayer;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tim
 *
 */
public class WorkerTest {

	Back back;
	Worker worker;
	DummyPlayer player;
	Map<String, Integer> resources;
	
	/**
	 * 
	 */
	public WorkerTest() {
		init();
		simulateGetDeliver();
		simulateLoopGetDeliver();
	}
	
	public void init() {
		back = Back.getInstance();
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
		 player = new DummyPlayer();
		initMap();
		
		back.addPlayer(player);
		
		worker = new Worker("worker0");
		worker.setType("worker");
		worker.setX(10);
		worker.setY(10);
		back.addUnit(player, worker);
		
		
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

	private void simulateGetDeliver(){
		back.nextPlayer();
		back.nextStep();
		
		PlayerOrder order = new PlayerOrder();
		order.setAction(ActionType.RESOURCES);
		order.getInfo().put("start", new Point(16,16));
		resources = new HashMap<String, Integer>();
		resources.put("iron", 30);
		resources.put("oil", 10);
		order.setResources(resources);
		
		worker.setPlayerOrder(order);
		
		do {
			worker.doLogic();
			System.out.println("worker has done logic");
		} while (worker.getState() == UnitState.ACTIVE);
		System.out.println("location" + worker.getX() + ":" + worker.getY());
	}
	
	private void simulateLoopGetDeliver(){
		resources.put("iron", 30);
		resources.put("oil", 10);
		simulateGetDeliver();
	}
	
	
	public static void main(String args[]) {
		new WorkerTest();
	}

}
