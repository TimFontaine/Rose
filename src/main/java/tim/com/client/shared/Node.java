/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Item;
import tim.data.back.Road;
import tim.data.back.TileImprovement;
import tim.data.back.TileItemContainer;
import tim.game.ai.data.MutableResource.Resource;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.TerrainType;
import tim.pathfinding.AStarNode;

/**
 * @author tim
 *
 */
public class Node implements Location, Serializable{
	int x;
	int y;
	private transient Node previousNode = null;
	
	private transient List<Unit> units;
	private transient Item item;
	private transient Road road;
	
	private Source source;
	
	private transient Player owner;
	
	private transient TerrainType terrainType;
	private transient City city;
	
	private transient TileItemContainer itemContainer;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		
		units = new ArrayList<Unit>();
		
		itemContainer = new TileItemContainer();
		
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
    
    public String getMapItemName() {
   		return item.getName();
    }
    
 	public Point getPoint() {
 		return new Point(x,y);
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
	public Node getPosition() {
		return this;
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

	/**
	 * @param tileImprovement
	 */
	public void addImprovement(TileImprovement tileImprovement) {
		itemContainer.addTileItem(tileImprovement);
	}

	public TileItemContainer getItemContainer() {
		return itemContainer;
	}

	public TerrainType getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(TerrainType terrainType) {
		this.terrainType = terrainType;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}
	
}
