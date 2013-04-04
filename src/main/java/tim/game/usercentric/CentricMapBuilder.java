/**
 * 
 */
package tim.game.usercentric;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Mine;
import tim.data.back.Oilwell;
import tim.data.back.ResourceItem;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.Map;
import tim.game.Player;
import tim.game.RoseRules;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;
import tim.pathfinding.PathfindingMap;

/**
 * @author tfontaine
 *
 */
public class CentricMapBuilder {
	
	Back world;
	private InterfaceTranslator interfaceTranslator;
	
	private int sizeX = 30;
	private int sizeY = 30;
	
	Map map;
	AStar aStar;
	private List<Player> playerList;
	private List<MapItem> mapItems;
	
	public CentricMapBuilder() {
		world = GameApplicationFactory.getInstance().getBack();
		mapItems = new ArrayList<MapItem>();
//		init();
	}
	
	public void construct() {
		constructMap();
		constructPathfinding();
		constructPlayers();
		assemble();
		constructUnits();
		setGraphics();
		startGame();
	}
	
	public void constructPathfinding() {
		PathfindingMap pathfindingMap = new PathfindingMap(map);
		aStar = new AStar(pathfindingMap, new ClosestHeuristic());
	}
	
	public void assemble() {
		world.setMap(map);
		world.setAStar(aStar);
		RoseRules r =  GameApplicationFactory.getInstance().getRoseRules();
		r.setPlayerList(playerList);
	}
	
	public void init() {
		RoseObjectFactory factory = RoseObjectFactory.getInstance();
//		back.buildOnTile(3, 3, "flag");
		
		PlayerData playerData = new PlayerData();
		PlayerData playerDataAi = new PlayerData();
//		
		Unit unit = factory.getUnit("worker");
		Unit unitAi = factory.getUnit("worker");
		
//		Actor worker = unit.getActor();
//		Actor workerAi = unit.getActor();
//		WorkerActor workerAi = new WorkerActor();
		List<Actor> units = new ArrayList<Actor>();
		List<Actor> unitsAI = new ArrayList<Actor>();
//		units.add(worker);
//		unitsAI.add(workerAi);
//		unitsAI.add(workerAi);
		CentricAIPlayer aiPlayer = new CentricAIPlayer();
		playerData.setActors(units);
		playerDataAi.setActors(unitsAI);
		
		
		interfaceTranslator = new InterfaceTranslator();
		back.addUnit(unit);
		back.addUnit(aiPlayer, unitAi);
		back.addHumam(interfaceTranslator);
		back.addPlayer(interfaceTranslator);
		back.addPlayer(aiPlayer);
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

	/**
	 * 
	 */
	public void constructMap() {
		map = new Map(sizeX, sizeY);
		//allocate resources;
//		Item mine = new Mine("mine");
//		Item oilwell = new Oilwell("oilwell");
//		mine.setImageName("mine");
//		mine.setX(2);
//		mine.setY(2);
//		mine.setType("mine");
		
		ResourceItem mine = new ResourceItem("mine", Resource.IRON);
		mine.setLocation(new Point(2, 2));
		map.getNode(2, 2).setResource(Resource.IRON);
		map.getNode(6, 8).setResource(Resource.OIL);
//		map.addItem(mine, 2,2);
	
//		oilwell.setType("oilwell");
//		oilwell.setImageName("oilwell");
//		oilwell.setX(6);
//		oilwell.setY(8);
//		
//		map.addItem(oilwell, 6,8);
		mapItems.add(mine);
//		mapItems.add(oilwell);
		
	}
	
	public void setGraphics() {
		world.setMapItems(mapItems);
	}
	
	public void constructPlayers() {
		playerList = new ArrayList<Player>();
		PlayerHand hand = new PlayerHand();
		PlayerHand handAi = new PlayerHand();
		InterfaceTranslator trans = new InterfaceTranslator();
		playerList.add(hand);
		playerList.add(handAi);
	}
	
	public void constructUnits() {
		for (Player player : playerList) {
			Unit unit = new Unit("worker","worker");
			player.addUnit(unit);
			mapItems.add(unit);
			world.addUnit(player, unit, new Point(0,0));
		}
	}
	
	private void startGame() {
		RoseRules r =  GameApplicationFactory.getInstance().getRoseRules();
		r.startGame();
	}

	/**
	 * @return
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * @return
	 */
	public List<MapItem> getMapItems() {
		return mapItems;
	}

}