/**
 * 
 */
package tim.data.back;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tim.com.client.City;
import tim.com.client.Locatable;
import tim.com.client.Location;
import tim.com.client.Unit;
import tim.game.Player;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.usercentric.Actor;
import tim.pathfinding.AStarNode;

/**
 * @author tim
 *
 */
public class Node implements Location, Serializable{
	int x;
	int y;
	private transient Node previousNode = null;
	
	private List<Unit> units;
	private Item item;
	private Road road;
	
	
	private Player owner;
	
	private Resource resource;//oil, iron
	private Terrain terrain;
	private City city; 
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		
		units = new ArrayList<Unit>();
		terrain = Terrain.PLAIN;
		resource = Resource.NONE;
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Point getLocation() {
		return new Point(x, y);
	}

	/**
	 * @return the previousNode
	 */
	public Node getPreviousNode() {
		return previousNode;
	}

	/**
	 * @param previousNode the previousNode to set
	 */
	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}
	

    public boolean equals(Node node) {
        return (node.x == x) && (node.y == y);
    }
    
    public boolean containsUnit() {
    	if (units.isEmpty()) {
    		return false;
    	}
   		return true;
    }
    
    public boolean containsMapItem() {
    	if (containsUnit() || item != null) {
    		return true;
    	}
    	return false;
    }
    
    public boolean containsItem() {
    	if (item != null) {
    		return true;
    	}
    	return false;
    }
    
    public boolean containsMapItemOfType(String type) {
    	for (Unit unit : units) {
    		if (unit.getType().equals(type)) {
    			return true;
    		}
    	}
    	
    	if (item != null && item.getType().equals(type)) {
    		return true;
    	}
    	return false;
    }
    
    public String getMapItemName() {
   		return item.getName();
    }
    
    
     
    public void addUnit(Unit unit) {
    	units.add(unit);
    	updateOwner();
    }

	public void addUnit(Actor actor) {
    	units.add((Unit) actor);
    }
    
    public void removeUnit(Unit unit) {
    	units.remove(unit);
    	updateOwner();
    }
    
    @Deprecated
    public void removeUnit(Actor actor) {
    	Unit unit = (Unit) actor;
    	units.remove(unit);
    }
    
    public boolean containsUnit(String name) {
    	for (Unit unit : units) {
    		if (unit.getName().equals(name)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean containsResource() {
    	if (resource != null) {
    		return true;
    	}
    	return false;
    }
    
    public Unit getFirstUnitOfType(String name) {
    	for (Unit unit: units) {
    		if (unit.getType().equals(name)) {
    			return unit;
    		}
    	}
    	return null;
    }
    

    /**
	 * @param unit
	 */
	private void updateOwner() {
		if (containsMapItem()) {
			if (containsUnit()) {
				owner = units.get(0).getPlayer();
			} else {
				owner = item.getPlayer();
			}
		} else {
			owner = null;
		}
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}
	
	public boolean containsRoad() {
		if (getRoad() != null) {
			return true;
		}
		return false;
	}

	/**
	 * @param item2
	 */
	public void removeItem(Item item) {
		item = null;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#add(tim.com.client.Locatable)
	 */
	@Override
	public void add(Locatable locatable) {
		units.add((Unit) locatable);
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#remove(tim.com.client.Locatable)
	 */
	@Override
	public void remove(Locatable locatable) {
		units.remove(locatable);
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#contains(tim.com.client.Locatable)
	 */
	@Override
	public boolean contains(Locatable locatable) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#getPosition()
	 */
	@Override
	public Point getPosition() {
		return new Point(x, y);
	}

	/**
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
		
	}

	/**
	 * @return
	 */
	public City getCity() {
		return city;
	}
	
}
