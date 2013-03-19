/**
 * 
 */
package tim.game;

import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Node;
import tim.data.back.Oilwell;
import tim.data.back.Path;
import tim.data.back.Thing;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;

/**
 * @author tim
 *
 */
public class MapBuilder {
	
	Back back;
	

	public MapBuilder() {
		back = GameApplicationFactory.getInstance().getBack();
		init();
	}
	
	public void init() {
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
//		back.buildOnTile(3, 3, "flag");
		
		Player playerAI = (Player) factory.getRoseObject("player");
//		back.addPlayer(human);
		back.addPlayer(playerAI);
		
		back.getPlayer().x = 0;
		back.getPlayer().y = 0;
//		back.getOtherPlayer().x = 10;
//		back.getOtherPlayer().y = 10;
		
		Building building = new Factory("factory");
		Item mine = new Mine("mine");
		Item oilwell = new Oilwell("oilwell");
		building.setImageName("factory");
		mine.setImageName("mine");
//		back.buildOnTile(2, 2, "mine");
		mine.setX(2);
		mine.setY(2);
		mine.setType("mine");
		
//		back.buildOnTile(4, 2, "factory");
		building.setX(4);
		building.setY(2);
//		building.setPlayer(playerAI);
		building.setType("factory");
		back.addItem(mine);
		back.addBuilding(building);
		back.addItem(oilwell);
		

		oilwell.setX(6);
		oilwell.setY(8);
		oilwell.setType("building");
		oilwell.setImageName("oilwell");
		
		
		Unit unit = (Unit) factory.getRoseObject("truck");
		unit.setX(5);
		unit.setY(5);
		unit.setOil(10);
//		unit.setPlayer(playerAI);
		
		Unit oilTruck = (Unit) factory.getRoseObject("oilTruck");
		oilTruck.setX(15);
		oilTruck.setY(15);
		oilTruck.setOil(1000);
		
		back.addUnit(playerAI, unit);
		back.addUnit(playerAI, oilTruck);
		
		back.nextPlayer();
	}
}
