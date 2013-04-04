/**
 * 
 */
package tim.data.back;

import tim.data.unit.TransferResource;

/**
 * @author tfontaine
 *
 */
public class Oilwell extends ResourceItem implements TransferResource {

	/**
	 * @param name
	 */
	public Oilwell(String name) {
		super(name);
		// TODO Auto-generated constructor stub
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

}
