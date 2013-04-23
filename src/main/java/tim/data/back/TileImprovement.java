/**
 * 
 */
package tim.data.back;

import tim.com.client.Location;

/**
 * @author tfontaine
 *
 */
public class TileImprovement extends TileItem {
	
	TileImprovementType type;

	/**
	 * 
	 */
	public TileImprovement(TileImprovementType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Locatable#getLocation()
	 */
	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.Locatable#setLocation(tim.com.client.Location)
	 */
	@Override
	public void setLocation(Location newLocation) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tim.data.back.TileItem#getId()
	 */
	@Override
	public String getId() {
		return type.getId();
		
	}

}
