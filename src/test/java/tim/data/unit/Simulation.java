/**
 * 
 */
package tim.data.unit;

import java.util.Map;

import org.junit.Before;

import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.game.Back;
import tim.game.Player;
import tim.game.ai.DummyPlayer;
import tim.game.ai.SimplePlayerAI;
import tim.game.factory.MockApplicationFactory;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tim
 *
 */
public class Simulation {
	
	Back back;
	Worker worker;
	Player player;
	Map<String, Integer> resources;

	/**
	 * 
	 */
	public Simulation() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void init() {
		back = MockApplicationFactory.getInstance().getBack();
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
		 player = new SimplePlayerAI("player");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Simulation();
	}

}
