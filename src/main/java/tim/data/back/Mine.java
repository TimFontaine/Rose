/**
 * 
 */
package tim.data.back;

import tim.data.unit.TransferResource;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.usercentric.ResourceEntity;

/**
 * @author tfontaine
 *
 */
public class Mine extends Item implements TransferResource, ResourceEntity {
	
	private Resource provides;
	int storage = 100;

	/**
	 * @param name
	 */
	public Mine(String name) {
		super(name);
		provides = Resource.IRON; 
	}
	
	public int retreive () {
		return storage;
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#receiveResource(java.lang.String, int)
	 */
	@Override
	public void receiveResource(String name, int amount) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#giveResource(java.lang.String, int)
	 */
	@Override
	public void giveResource(String name, int amount) {
		// TODO Auto-generated method stub
		
	}

	public Resource provides() {
		return provides;
	}

	public void setProvides(Resource provides) {
		this.provides = provides;
	}

}
