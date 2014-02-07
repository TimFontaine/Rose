/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;

import tim.com.client.Game;
import tim.data.back.Direction;
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
	
	protected Game game;
	
	enum UNITSTATE {
		ACTIVE,
		IN_CITY;
	};
	
	public UNITSTATE state;

	private String id;

	/**
	 * @param string 
	 * 
	 */
	public Unit(UnitType unitType, Player owner, Game game) {
		this.unitType = unitType;
		this.owner = owner;
		this.game = game;
		id = UUID.randomUUID().toString();
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

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 *@TODO move this function to unit or node/tile class
	 */
	protected Point translateMove(Direction direction) {
		Point newLocation = getLocation().getPosition().getPoint();
		switch (direction) {
		case DOWN:
			newLocation.y++;
			break;
		case UP:
			newLocation.y--;
			break;
		case LEFT:
			newLocation.x--;
			break;
		case RIGHT:
			newLocation.x++;
			break;
		default:
			break;
		}
		
		return newLocation;
	}

}
