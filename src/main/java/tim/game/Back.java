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

import tim.data.back.Direction;
import tim.data.back.Event;
import tim.data.back.Factory;
import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.back.Road;
import tim.data.back.Thing;
import tim.data.building.Building;
import tim.data.unit.Unit;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.usercentric.Actor;
import tim.game.usercentric.InterfaceTranslator;
import tim.game.usercentric.ResourceEntity;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;
import tim.pathfinding.Dijkstra;

/**
 * @author tfontaine
 *
 */
public class Back {
	
	private Map map;
	private List<Event> events;
	
	private List<Player> playerList;
	private Player activePlayer;
	Iterator<Player> playerIterator;
	private InterfaceTranslator trans;

	public static final int defaultSpeed = 10;

	private Point bounderies;
	
	
	private int itemId;
	
	/**
	 * 
	 */
	public Back() {
		map = new Map();
		events = new ArrayList<Event>();
		playerList = new ArrayList<Player>();
	}
	
	/**
	 * 
	 */
	public void startGame() {
		playerIterator = playerList.listIterator();
		nextPlayer();//take the first plaer from the list
		nextStep();
	}
	

	public List<MapItem> getMapItems() {
		return map.getMapItems();
	}
	
	public void buildOnTile(int x, int y, Building building) {
		map.getNode(x, y).setItem(building);
		building.setLocation(new Point(x,y));
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
	
	public Path findShortestPath(int startX, int startY, int endX, int endY) {
		initSearchMap();
		AStar star = new AStar(map, new ClosestHeuristic());
		Path path = star.findShortestPath(startX, startY, endX, endY);
		return path;
	}
	
	public void moveUnit(Point source, Point target, Actor actor) {
		map.getNode(source.x, source.y).removeUnit(actor);
		map.getNode(target.x, target.y).addUnit(actor);
		System.out.println("moved unit with name" + ((Unit)actor).getName());
	}
	
	public void moveUnit(Unit unit, int x, int y) {
		Point location = unit.getLocation(); 
		map.getNode(location.x, location.y).removeUnit(unit);
		map.getNode(x, y).addUnit(unit);
		unit.setLocation(new Point(x, y));
	}
	
	public Path findNearestObject(Unit unit, String itemName) {
		return findNearestObject(unit.getLocation().x, unit.getLocation().y, itemName);
	}
	
	public Path findNearestObject(int startX, int startY, String itemName) {
		initSearchMap();
		Dijkstra dijkstra = new Dijkstra();
		Path path = dijkstra.findClosestItem(startX, startY, map, itemName);
		return path;
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
		map.shuffleNeighbours();
		map.resetNodes();
	}
	
	public void nextPlayer() {
		events.clear();
		if (playerIterator.hasNext()) {
			activePlayer = playerIterator.next();
		} else {
			playerIterator = playerList.iterator();
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
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public List<Event> getEvents() {
		return events;
	}	

	public List<Player> getPlayerList() {
		return playerList;
	}

	//for the mapbuilder
	public void addUnit(Player player, Unit unit) {
		System.out.println("adding unit to player");
		String name = unit.getName() + itemId++;
		unit.setName(name);
		getMapItems().add(unit);
		player.addActor(unit.getActor());
		unit.setPlayer(player);
		map.getNode(unit.getX(), unit.getY()).addUnit(unit);
	}
	
	public void addUnit(Unit unit) {
		System.out.println("adding unit");
		String name = unit.getName() + itemId++;
		unit.setName(name);
		getMapItems().add(unit);
		activePlayer.addActor(unit.getActor());
		unit.setPlayer(activePlayer);
		map.getNode(unit.getX(), unit.getY()).addUnit(unit);
	}
	
		
	public void removeUnit(Unit unit) {
		map.getNode(unit.getX(), unit.getY()).removeUnit(unit);
		map.getMapItems().remove(unit);
	}
	
	//add an item if it used by another item
	public void addUsedItem(Item item) {
		activePlayer.addUsedItem(item);
	}
	
	public void createMap(int sizeX, int sizeY) {
		map.setupTiles(sizeX, sizeY, 1);
		setBounderies(new Point(sizeX, sizeY));
	}
	
	public void addItem(Item item) {
		getMapItems().add(item);
		map.getNode(item.getX(), item.getY()).setItem(item);
	}
	
	public void addBuilding(Building building) {
		addItem(building);
	}
	
	public void addBuilding(Player player, Building building) {
		addItem(building);
		player.addBuilding(building);
	}

	public Node getNode(int x, int y) {
		return map.getNode(x, y);
	}

	public InterfaceTranslator getTrans() {
		return trans;
	}

	public void setTrans(InterfaceTranslator trans) {
		this.trans = trans;
	}
	
	public void addHumam(InterfaceTranslator trans) {
		this.trans = trans;
	}
	
	public boolean containsEnemy(Point location) {
		Node target = getNode(location.x, location.y);
		Player owner = target.getOwner();
		if (owner != null && activePlayer != target.getOwner()) {
			return true;
		}
		return false;
	}

	/**
	 * remove buildings and units from tile
	 */
	public void clearNode(Point point) {
		Node node = getNode(point.x, point.y);
		for (Unit unit : node.getUnits()) {
			map.getMapItems().remove(unit);
		}
		if (node.containsItem()) {
			Item item = node.getItem();
			node.removeItem(item);
			map.getMapItems().remove(item);
		}
	}

	public Point getBounderies() {
		return bounderies;
	}

	public void setBounderies(Point bounderies) {
		this.bounderies = bounderies;
	}

	/**
	 * @param location
	 */
	public void attack(Unit attacker, Point location) {
		Node target = map.getNode(location.x, location.y);
		Unit defender = target.getUnits().get(0);
		
		int defence = defender.getStrength();//ignore for the moment
		int attack = attacker.getAttack();
		int defenderStrength = defender.getStrength();
		
		defenderStrength-= attack;
		if (defenderStrength < 0) {
			//defender dies
			removeUnit(defender);
		} else {
			//update defender strength
			defender.setStrength(defenderStrength);
		}
		
	}
	
	public Resource getResourceEntitiy(Point location) {
		Node target = map.getNode(location.x, location.y);
		if (target.containsItem() && target.getItem() instanceof ResourceEntity) {
			ResourceEntity e = (ResourceEntity)target.getItem();
			return e.provides();
		}
		return Resource.NONE;
	}

	/**
	 * @param workerActor
	 * @param point
	 */
	public void transferResource(Unit unit , Point location) {
		Node target = map.getNode(location.x, location.y);
		ResourceEntity e = (ResourceEntity)target.getItem();
		//retreive from entity;
		int amount = e.retreive();
		Resource resource = e.provides();
		
		unit.getResourceContainer().addResource(resource, amount);
	}

	

}
