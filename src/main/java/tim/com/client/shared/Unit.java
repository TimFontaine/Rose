/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;
import java.io.Serializable;

import tim.game.Player;
import tim.namespacetest.types.Ability;
import tim.namespacetest.types.UnitType;

/**
 * @author tfontaine
 *
 */
public class Unit implements Locatable, Location, Serializable {
	
	private UnitType unitType;
	
	Location location;//tile,building, carrier
	
	protected transient Player owner;
	
	enum UNITSTATE {
		ACTIVE,
		IN_CITY;
	};
	
	public UNITSTATE state;

	/**
	 * @param string 
	 * 
	 */
	public Unit(UnitType unitType, Player owner) {
		this.unitType = unitType;
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#add(tim.com.client.Locatable)
	 */
	@Override
	public void add(Locatable locatable) {
		
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Location#remove(tim.com.client.Locatable)
	 */
	@Override
	public void remove(Locatable locatable) {
		throw new UnsupportedOperationException();
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
	public Node getPosition() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return
	 */
	public Node getTile() {
		return (Node) location;
	}

	/**
	 * @return the unitType
	 */
	public UnitType getUnitType() {
		return unitType;
	}

	/**
	 * @return the state
	 */
	public UNITSTATE getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(UNITSTATE state) {
		this.state = state;
	}
	
	public boolean hasAbility(String name) {
		for (Ability ability : unitType.getAbility()) {
			if (name.equals(ability.getAbilityType())) {
				return true;
			}
		}
		return false;
	}

}
