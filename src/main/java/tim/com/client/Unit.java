/**
 * 
 */
package tim.com.client;

import java.awt.Point;

import tim.data.back.Node;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class Unit implements Locatable, Location {
	
	private String type;
	
	Location location;//tile,building, carrier
	
	protected Player owner;

	/**
	 * @param string 
	 * 
	 */
	public Unit(String type) {
		this.type = type;
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
	
	public void move(Location newLocation) {
		if (this.location != null) {
			this.location.remove(this);
		}
		this.location = newLocation;
		location.add(this);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Locatable#getLocation()
	 */
	@Override
	public Location getLocation() {
		return location;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Locatable#setLocation(tim.com.client.Locatable)
	 */
	@Override
	public void setLocation(Location newLocation) {
		move(newLocation);
		((RosePlayer)owner).explore((Node) newLocation);
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#getPosition()
	 */
	@Override
	public Point getPosition() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
