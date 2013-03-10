/**
 * 
 */
package tim.data.unit;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

	static Back back;
	Worker worker;
	DummyPlayer player;
	Map<String, Integer> resources;
	Factory factory;
	
	/**
	 * 
	 */
	public WorkerTest() {
	}
	
	@BeforeClass
	public static void sstartUp() {
//		back = Back.getInstance();
	}
	
	
	@Before
	public void init() {
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
		 player = new DummyPlayer();
		initMap();
		
		worker = new Worker("worker0");
		worker.setType("worker");
		worker.setX(10);
		worker.setY(10);
		
		initPlayerOrder();
		back.addPlayer(player);
		
	
		back.addUnit(player, worker);
		back.nextPlayer();
		
		
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
		
		factory = new Factory("factory0");
		factory.setX(16);
		factory.setY(16);
		factory.setType("factory");
		back.addBuilding(player, factory);
		
	}
	
	private void initPlayerOrder() {
		PlayerOrder order = new PlayerOrder();
		order.setAction(ActionType.RESOURCES);
		order.getInfo().put("start", new Point(16,16));
		resources = new HashMap<String, Integer>();
		resources.put("iron", 30);
		resources.put("oil", 10);
//		order.setResources(resources);
//		
//		worker.setPlayerOrder(order);
	}

	@Test
	public void simulateGetDeliver(){
//		back.nextStep();
		simulate();
		
		Point destination = factory.getLocation();
		assertEquals(destination, worker.getLocation());
//		assertTrue(factory.getResources().containsKey("iron"));
//		assertEquals(new Integer(30), factory.getResources().get("iron"));
		System.out.println("location" + worker.getX() + ":" + worker.getY());
	}
	
	@Test
	public void simulateLoopGetDeliver(){
		simulate();
		simulate();
	}
	
	private void simulate() {
		do {
			worker.doLogic();
			System.out.println("worker has done logic");
		} while (worker.getState() == UnitState.ACTIVE);
	}
	
	
	public static void main(String args[]) {
		new WorkerTest();
	}

}
