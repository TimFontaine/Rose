/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.com.client.Game;
import tim.game.Player;
import tim.namespacetest.types.Resource;
import tim.namespacetest.types.UnitType;

/**
 * @author tfontaine
 *
 */
public class City implements Location {
	
	private Node tile;
	private Player owner;
	
//	List<Unit> units;
	private List<Building> buildings;
	
	private Cargo cargo;
	
	private Game game;

	/**
	 * @param player
	 * @param tile
	 */
	public City(Game game, Player player, Node tile) {
//		units = new ArrayList<Unit>();
		
		this.owner = player;
		this.tile = tile;
		this.game = game;
		cargo = new Cargo();
		cargo.setMaxStorage(game.getSpecification().getTileItem("city").getSpace());
		
		
		//init resources
		Resource iron = new Resource();
		iron.setAmount(4);
		iron.setResourceType("iron");
		cargo.addResource("iron", 4);
	}
	
	public void placeCity() {
		tile.setCity(this);
	}

	/**
	 * 
	 */
	public void contstructBuilding(String buildingName) {
		System.out.println("city has order to build:" + buildingName);
	}
	
	public void addUnit(Unit unit) {
		tile.getUnits().add(unit);
		/**TODO
		 * move section to unit move->no moves or go in city (fortify ?)
		 */
//		unit.setState(UNITSTATE.IN_CITY);
	}
	
	public void removeUnit(Unit unit) {
		tile.getUnits().remove(unit);
	}
	
	public List<Unit> getUnits() {
		return tile.getUnits();
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#add(tim.com.client.Locatable)
	 */
	@Override
	public void add(Locatable locatable) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#remove(tim.com.client.Locatable)
	 */
	@Override
	public void remove(Locatable locatable) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#contains(tim.com.client.Locatable)
	 */
	@Override
	public boolean contains(Locatable locatable) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addBuilding(Building building) {
		buildings.add(building);
	}
	
	public boolean orderUnit(String unitName) {
		UnitType type = game.getSpecification().getUnitType(unitName);
		List<Resource> resources = type.getCost();
		int spaceUsage = 0;
		for (Resource resource : resources) {
			boolean b = cargo.hasResource(resource);
			if (!b) {
				return false;
			}
			spaceUsage += resource.getAmount(); 
		}
		
		if (!cargo.isSpaceAvailable(spaceUsage)) {
			return false;
		}
		
		//all tests ok, use resources
		for (Resource resource : resources) {
			cargo.useResource(resource);
		}
		
		Unit unit = new Unit(type, owner);
		unit.setLocation(getPosition());
//		city.addUnit(unit);
		
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see tim.com.client.Location#getPosition()
	 */
	@Override
	public Node getPosition() {
		return tile;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	

}
