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
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.ai.job.Job.JobType;
import tim.game.factory.GameApplicationFactory;
import tim.game.factory.RoseObjectFactory;
import tim.game.usercentric.Actor;
import tim.game.usercentric.CentricMapBuilder;
import tim.game.usercentric.ComplexOrder;
import tim.game.usercentric.InterfaceTranslator;
import tim.game.usercentric.ResourceEntity;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;
import tim.pathfinding.Dijkstra;
import tim.pathfinding.PathfindingMap;

/**
 * @author tfontaine
 *
 */
public class Back {
	
	private Map map;
	private PathfindingMap pathfindingMap;
	private List<Event> events;
	
	private List<Player> playerList;
	private Player activePlayer;
	private Unit activeUnit;
	private Building activeBuilding;
	Iterator<Player> playerIterator;
	private InterfaceTranslator trans;

	public static final int defaultSpeed = 10;

	private Point bounderies;
	
	
	private int itemId;
	
	private List<MapItem> mapItems;
	
	GameApplicationFactory applicationFactory;
	AStar aStar;
	
	/**
	 * 
	 */
	public Back() {
		events = new ArrayList<Event>();
		playerList = new ArrayList<Player>();
		mapItems = new ArrayList<MapItem>();
	}

	public List<MapItem> getMapItems() {
		return mapItems;
	}
	
	public Path findShortestPath(int startX, int startY, int endX, int endY) {
		aStar.resetMap();
		Path path = aStar.findShortestPath(startX, startY, endX, endY);
		return path;
	}
	
	@Deprecated
	public void moveUnit(int x, int y) {
		Unit unit = activeUnit;
		Point location = unit.getLocation();
		map.removeUnit(unit, location.x, location.y);
		map.addUnit(unit, x, y);
		unit.setLocation(new Point(x, y));
	}
	
	public void moveUnit(Unit unit, int x, int y) {
		Point location = unit.getLocation();
		map.removeUnit(unit, location.x, location.y);
		map.addUnit(unit, x, y);
		unit.setLocation(new Point(x, y));
	}
	
	public void gotoLocation(Point destination) {
		ComplexOrder order = new ComplexOrder();
		Job job = new GotoJob(activeUnit, destination);
		job.setType(JobType.PATH);
		order.addJob(job);
		activeUnit.setComplexOrder(order);
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
	
	public List<Event> getEvents() {
		return events;
	}	

	//for the mapbuilder
	public void addUnit(Player player, Unit unit, Point location) {
		System.out.println("adding unit to player");
		String name = unit.getName() + itemId++;
		unit.setName(name);
		getMapItems().add(unit);
		unit.setPlayer(player);
		map.addUnit(unit, location.x, location.y);
		unit.setLocation(location);
	}
	
	public void addUnit(Unit unit, Point location) {
		addUnit(activePlayer, unit, location);
	}
	
		
	public void removeUnit(Unit unit) {
		map.removeUnit(unit, unit.getLocation().x, unit.getLocation().y);
		getMapItems().remove(unit);
	}
	
	public void addBuilding(Building building, Point location) {
		addBuilding(activePlayer, building, location);
	}
	
	public void addBuilding(Player player, Building item, Point location) {
		map.addItem(item, location.x, location.y);
		player.addBuilding(item);
		item.setLocation(location);
		mapItems.add(item);
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
	
	public Point getBounderies() {
		return map.getBounderies();
	}

	public void setBounderies(Point bounderies) {
		this.bounderies = bounderies;
	}

	/**
	 * @param location
	 */
	public void attack(Point location) {
		Node target = map.getNode(location.x, location.y);
		Unit defender = target.getUnits().get(0);
		
		int defence = defender.getStrength();//ignore for the moment
		int attack = activeUnit.getAttack();
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

	public void setMapItems(List<MapItem> mapItems) {
		this.mapItems = mapItems;
	}

	/**
	 * @return
	 */
	public Unit getActiveUnit() {
		if (activeUnit == null) {
			activeUnit = activePlayer.provideUnit();
		}
		return activeUnit;
	}

	/**
	 * 
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * add a building on the position of the activeunit
	 * TODO, use resources 
	 */
	public void addBuilding(String type) {
		Building building = RoseObjectFactory.getInstance().getBuilding(type, activeUnit);
		addBuilding(building, building.getLocation());
		System.out.println("building added on" + building.getLocation());
	}

	/**
	 * @param aStar2
	 */
	public void setAStar(AStar aStar) {
		this.aStar = aStar;
	}
	
	public void switchSelectedBuilding(Building building) {
		this.activeBuilding = building;
	}

	public Building getActiveBuilding() {
		return activeBuilding;
	}

	

}
