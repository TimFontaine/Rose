/**
 * 
 */
package tim.game;

import java.awt.Point;

import tim.data.back.Building;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.data.unit.Unit;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class SimpleMapBuilder {

	/**
	 * 
	 */
	Back back;

	public SimpleMapBuilder() {
		back = GameApplicationFactory.getInstance().getBack();
		init();
	}
	
	public void init() {
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
//		back.buildOnTile(3, 3, "flag");
		
		Player playerAI = (Player) factory.getRoseObject("simplePlayer");
//		back.addPlayer(human);
		back.addPlayer(playerAI);
		
		back.getPlayer().x = 0;
		back.getPlayer().y = 0;
//		back.getOtherPlayer().x = 10;
//		back.getOtherPlayer().y = 10;
		
//		Building building = new Factory("factory");
		Item mine = new Mine("mine");
		Item oilwell = new Oilwell("oilwell");
//		building.setImageName("factory");
		mine.setImageName("mine");
//		back.buildOnTile(2, 2, "mine");
		mine.setX(2);
		mine.setY(2);
		mine.setType("mine");
		
//		back.buildOnTile(4, 2, "factory");
//		building.setX(4);
//		building.setY(2);
//		building.setPlayer(playerAI);
//		building.setType("factory");
		back.addItem(mine);
//		back.addBuilding(playerAI, building);
		
		

		oilwell.setX(6);
		oilwell.setY(8);
		oilwell.setType("oilwell");
		oilwell.setImageName("oilwell");
		
		back.addItem(oilwell);
		
		
//		Unit unit = (Unit) factory.getRoseObject("truck");
//		unit.setX(5);
//		unit.setY(5);
//		unit.setOil(10);
//		unit.setPlayer(playerAI);
//		
//		Unit oilTruck = (Unit) factory.getRoseObject("oilTruck");
//		oilTruck.setX(15);
//		oilTruck.setY(15);
//		oilTruck.setOil(1000);
//		oilTruck.setPlayer(playerAI);
		
		Unit unit = (Unit) factory.getRoseObject("worker");
		unit.setX(10);
		unit.setY(15);
		back.addUnit(playerAI, unit);
//		back.addUnit(playerAI, unit);
//		back.addUnit(playerAI, oilTruck);
		
		back.nextPlayer();
	}

}
