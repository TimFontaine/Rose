/**
 * 
 */
package tim.game.usercentric;

import java.util.ArrayList;
import java.util.List;

import tim.data.back.Item;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.Player;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class CentricMapBuilder {
	
	Back back;
	private InterfaceTranslator interfaceTranslator;
	
	public CentricMapBuilder() {
		back = GameApplicationFactory.getInstance().getBack();
		init();
	}
	
	public void init() {
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
		back.getMap().setupTiles(30,30, Back.defaultSpeed);
//		back.buildOnTile(3, 3, "flag");
		
		PlayerData playerData = new PlayerData();
		PlayerData playerDataAi = new PlayerData();
//		
		Unit unit = factory.getUnit("worker");
		
		Actor worker = unit.getActor();
//		WorkerActor workerAi = new WorkerActor();
		List<Actor> units = new ArrayList<Actor>();
		List<Actor> unitsAI = new ArrayList<Actor>();
		units.add(worker);
//		unitsAI.add(workerAi);
		CentricAIPlayer aiPlayer = new CentricAIPlayer(playerDataAi);
		playerData.setActors(units);
		playerDataAi.setActors(unitsAI);
		interfaceTranslator = new InterfaceTranslator(playerData);
		
		back.addHumam(interfaceTranslator);
		back.addPlayer(interfaceTranslator);
//		back.addPlayer(aiPlayer);
		back.addUnit(interfaceTranslator, unit);
		//back.addUnit(workerAi);
		
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
		
//		Unit unit = (Unit) factory.getRoseObject("worker");
//		unit.setX(10);
//		unit.setY(15);
//		back.addUnit(playerAI, unit);
//		back.addUnit(playerAI, unit);
//		back.addUnit(playerAI, oilTruck);
		
	}

	public InterfaceTranslator getInterfaceTranslator() {
		return interfaceTranslator;
	}

	public void setInterfaceTranslator(InterfaceTranslator interfaceTranslator) {
		this.interfaceTranslator = interfaceTranslator;
	}

}