/**
 * 
 */
package tim.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import tim.data.back.Building;
import tim.data.back.Direction;
import tim.data.back.Event;
import tim.data.back.EventCode;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.back.Road;
import tim.data.back.Thing;
import tim.data.unit.Unit;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;
import tim.pathfinding.Dijkstra;

/**
 * @author tfontaine
 *
 */
public class Back {
	
	Point player;
	private int x;
	private int y;
	private Map map;
	private List<Event> events;
//	private Thing flag;
	
	private List<Player> playerList;
	private Player activePlayer;
	Iterator<Player> playerIterator;

	public static final int defaultSpeed = 10;
	private java.util.Map<String, Integer> speed;
	
	private int itemId;
	/**
	 * 
	 */
	public Back() {
		map = new Map();
		events = new ArrayList<Event>();
		setPlayerList(new ArrayList<Player>());
		player = new Point();
//		otherPlayer = new Point();
//		flag = new Thing();
//		flag.setX(3);
//		flag.setY(3);
//		flag.setName("flag");
		
		speed = new HashMap<String, Integer>();
		speed.put("default", defaultSpeed);
		speed.put("road", 50);
		speed.put("double-road", 70);
	}
	
	public void move(Direction direction, int amount) {
		switch (direction) {
		case DOWN:
			player.y+=amount;
			break;
		case UP:
			player.y-=amount;
			break;
		case LEFT:
			player.x-=amount;
			break;
		case RIGHT:
			player.x+=amount;
			break;
		default:
			break;
		}
		checkWinConditions();
	}
	
	
//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

//	public void putThing() {
//		Thing thing = new Thing();
//		thing.setX(x);
//		thing.setY(y);
//		map.getThingList().add(thing);
//	}
	

	public List<MapItem> getMapItems() {
		return map.getMapItems();
	}
	
	public void buildOnTile(int x, int y, Building building) {
		map.getNode(x, y).setItem(building);
		building.setX(x);
		building.setY(y);
		map.getMapItems().add(building);
		activePlayer.addBuilding(building);
	}
	
	public void buildOnTile(int x, int y, Item item) {
		map.getNode(x, y).setItem(item);
		item.setX(x);
		item.setY(y);
		/**
		 * TODO
		 * improve draw function later
		 */
		map.getMapItems().add(item);
	}
	
	public void buildOnTile(int x, int y, Road road) {
		road.setX(x);
		road.setY(y);
		map.getNode(x, y).setRoad(road);
		map.getMapItems().add(road);
	}

	public void buildOnTile(int x, int y, String name) {
		Building building = new Factory("building");
		building.setX(x);
		building.setY(y);
		building.setName(name);
		building.setType(name);
		building.setImageName(name);
		System.out.println("item build:" + name);
		if (name.equals("block")) {
			getNode(building.getX(), building.getY()).setObstacle(true);
		}
		if (speed.get(name) != null) {
			map.getNode(x, y).setTravelWeight(speed.get(name));
			System.out.println("item build with speed:" + name + map.getNode(x, y).getTravelWeight());
		}
		map.getMapItems().add(building);
		map.getNode(x, y).setItem(building);
	}
	
	private void checkWinConditions() {
		for (MapItem mapItem : getMapItems()) {
			if (mapItem.getName().equals("home")) {
				if (player.getX() == mapItem.getX() && player.getY() == mapItem.getY()) {
					Event winEvent = new Event();
					winEvent.setDescription("you have won the game");
					events.add(winEvent);
				}
			}
		}
	}
	
	public Path calcPath(Point start) {
		removePath();
		getMap().shuffleNeighbours();
		map.resetNodes();
		AStar star = new AStar(map, new ClosestHeuristic());
//		Path path = star.findShortestPath(start.x, start.y, flag.getX(), flag.getY());
//		for (Node node: path.getPathNodes()) {
//			Thing thing = new Thing();
//			thing.setX(node.getX());
//			thing.setY(node.getY());
//			thing.setName("path");
//			back.getThingList().add(thing);
//		}
//		return path;
		return null;
	}
	
	public Path findShortestPath(int startX, int startY, int endX, int endY) {
		initSearchMap();
		AStar star = new AStar(map, new ClosestHeuristic());
		Path path = star.findShortestPath(startX, startY, endX, endY);
		return path;
	}
	
	public int moveUnit(Unit unit, int x, int y) {
		Node target = map.getNode(x, y);
		//tile blocked ?
//		if (target.getUnit() != null) {
//			return EventCode.TILE_BLOCKED;
//		}
		
		map.getNode(unit.getX(), unit.getY()).removeUnit(unit);
		map.getNode(x, y).addUnit(unit);
		unit.setX(x);
		unit.setY(y);
		return EventCode.SUCCESS;
	}
	
	public Path findNearestObject(Thing thing, String itemName) {
		return findNearestObject(thing.getX(), thing.getY(), itemName);
	}
	
	public Path findNearestObject(int startX, int startY, String itemName) {
		initSearchMap();
		Dijkstra dijkstra = new Dijkstra();
		Path path = dijkstra.findClosestItem(startX, startY, map, itemName);
		return path;
	}
	
	public void addPath(Path path) {
//		for (Node node: path.getPathNodes()) {
//			Item thing = new Item("path item");
//			thing.setX(node.getX());
//			thing.setY(node.getY());
//			thing.setName("path");
//			back.getThingList().add(thing);
//		}
	}
	
	private void removePath() {
		List<MapItem> newList = new ArrayList<MapItem>();
		
		List<MapItem> mapItems = map.getMapItems();
		for (MapItem mapItem: mapItems) {
			if (!mapItem.getName().equals("path")) {
				newList.add(mapItem);
			}
		}
		
		map.setMapItemsList(newList);
		
	}
	
	private void initSearchMap() {
		removePath();
		getMap().shuffleNeighbours();
		map.resetNodes();
	}
	

	public void updateSpeed(java.util.Map<String, Integer> travelSpeed) {
		for (int i = 0; i < map.getSizeX(); i++) {
			for (int j=0; j<map.getSizeY(); j++) {
				Node node = map.getNode(i, j);
				node.setTravelWeight(travelSpeed.get("default"));
			}
		}
		for (MapItem mapItem: map.getMapItems()) {
			if (travelSpeed.containsKey(mapItem.getName())) {
				int speed = travelSpeed.get(mapItem.getName());
				map.getNode(mapItem.getX(), mapItem.getY()).setTravelWeight(speed);
			}
		}
		this.speed = travelSpeed;
		
	}
	
	public void nextPlayer() {
//		playerIterator = playerList.iterator();
		events.clear();
		if (playerIterator == null) {
			playerIterator = playerList.listIterator();
		}
		if (playerIterator.hasNext()) {
			activePlayer = playerIterator.next();
		}
		activePlayer.initTurn();
	}
	
	public void nextStep() {
		activePlayer.doLogic();
	}
	
	public void nextTurn() {
		playerIterator = playerList.iterator();
	}
	
	public void move(Point point, int x, int y) {
		point.move(x, y);
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public Point getPlayer() {
		return player;
	}

	

	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}

	public java.util.Map<String, Integer> getSpeed() {
		return speed;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	//for the mapbuilder
	public void addUnit(Player player, Unit unit) {
		getMapItems().add(unit);
		player.addUnit(unit);
		map.getNode(unit.getX(), unit.getY()).addUnit(unit);
	}
	
	public void addUnit(Unit unit) {
		String name = unit.getName() + itemId++;
		unit.setName(name);
		getMapItems().add(unit);
		activePlayer.addUnit(unit);
		map.getNode(unit.getX(), unit.getY()).addUnit(unit);
	}
	
	//add an item if it used by another item
	public void addUsedItem(Item item) {
		activePlayer.addUsedItem(item);
	}
	
//	public void addBuilding(Player player, Building building) {
//		back.getMapItems().add(building);
//		player.addBuilding(building);
//		map.getNode(building.getX(), building.getY()).setBuilding(building);
//	}
	
	public void addItem(Item item) {
		getMapItems().add(item);
		map.getNode(item.getX(), item.getY()).setItem(item);
	}
	
	public void addBuilding(Building building) {
		addItem(building);
		building.getPlayer().addBuilding(building);
	}
	
	public void addBuilding(Player player, Building building) {
		addItem(building);
		player.addBuilding(building);
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public Node getNode(int x, int y) {
		return map.getNode(x, y);
	}

	
	

}
